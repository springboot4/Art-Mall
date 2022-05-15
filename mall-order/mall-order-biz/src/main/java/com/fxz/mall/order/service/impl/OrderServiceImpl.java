package com.fxz.mall.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.enums.BusinessTypeEnum;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.mp.result.Result;
import com.fxz.common.redis.util.BusinessNoGenerator;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.order.constant.OrderConstants;
import com.fxz.mall.order.dto.OrderItemDto;
import com.fxz.mall.order.dto.OrderSubmitDto;
import com.fxz.mall.order.entity.Item;
import com.fxz.mall.order.entity.Order;
import com.fxz.mall.order.enums.OrderStatusEnum;
import com.fxz.mall.order.enums.OrderTypeEnum;
import com.fxz.mall.order.mapper.OrderMapper;
import com.fxz.mall.order.service.OrderService;
import com.fxz.mall.order.vo.OrderConfirmVo;
import com.fxz.mall.order.vo.OrderSubmitVo;
import com.fxz.mall.product.dto.CheckPriceDTO;
import com.fxz.mall.product.dto.LockStockDTO;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.fxz.mall.user.dto.AddressDto;
import com.fxz.mall.user.feign.RemoteAddressService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	private final ItemServiceImpl orderItemService;

	private final ThreadPoolExecutor threadPoolExecutor;

	private final RemoteSkuService remoteSkuService;

	private final RemoteAddressService remoteAddressService;

	private final BusinessNoGenerator businessNoGenerator;

	private final RedisTemplate redisTemplate;

	/**
	 * 获取购买商品明细、用户默认收货地址、防重提交唯一token 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
	 * @param skuId 直接购买必填，购物车结算不填
	 * @return OrderConfirmVO
	 */
	@Override
	public OrderConfirmVo confirm(Long skuId) {
		OrderConfirmVo orderConfirmVO = new OrderConfirmVo();

		// 获取订单的商品明细信息
		CompletableFuture<Void> orderItemsCompletableFuture = CompletableFuture.runAsync(() -> {
			List<OrderItemDto> orderItems = this.getOrderItems(skuId);
			orderConfirmVO.setOrderItems(orderItems);
		}, threadPoolExecutor);

		// 获取会员收获地址
		CompletableFuture<Void> addressesCompletableFuture = CompletableFuture.runAsync(() -> {
			List<AddressDto> addresses = remoteAddressService.findAll().getData();
			orderConfirmVO.setAddresses(addresses);
		}, threadPoolExecutor);

		// 生成唯一token并缓存到redis，防止订单重复提交
		CompletableFuture<Void> orderTokenCompletableFuture = CompletableFuture.runAsync(() -> {
			String orderToken = businessNoGenerator.generate(BusinessTypeEnum.ORDER);
			orderConfirmVO.setOrderToken(orderToken);
			redisTemplate.opsForValue().set(OrderConstants.ORDER_TOKEN_PREFIX + orderToken, orderToken);
		}, threadPoolExecutor);

		CompletableFuture.allOf(orderItemsCompletableFuture, addressesCompletableFuture, orderTokenCompletableFuture)
				.join();
		log.info("订单确认响应：{}", orderConfirmVO);

		return orderConfirmVO;
	}

	/**
	 * 订单提交 todo 分布式事务
	 */
	@SneakyThrows
	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrderSubmitVo submit(OrderSubmitDto orderSubmitDto) {
		log.info("订单提交数据:{}", JSONUtil.toJsonStr(orderSubmitDto));

		// 订单基础信息校验
		List<OrderItemDto> orderItems = orderSubmitDto.getOrderItems();
		Assert.isTrue(CollectionUtil.isNotEmpty(orderItems), "订单没有商品");

		// 利用lua脚本进行订单重复提交校验
		String orderToken = orderSubmitDto.getOrderToken();
		DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(OrderConstants.RELEASE_LOCK_LUA_SCRIPT,
				Long.class);
		Long execute = (Long) this.redisTemplate.execute(redisScript,
				Collections.singletonList(OrderConstants.ORDER_TOKEN_PREFIX + orderToken), orderToken);
		Assert.isTrue(Objects.equals(execute, 1L), "订单不可重复提交");

		Order order;
		try {
			// 订单验价,校验下单时的价格和当前价格是否一样
			Long orderTotalAmount = orderSubmitDto.getTotalAmount();
			boolean checkResult = this.checkOrderPrice(orderTotalAmount, orderItems);
			Assert.isTrue(checkResult, "当前页面已过期，请重新刷新页面再提交");

			// 锁定商品库存
			this.lockStock(orderToken, orderItems);

			// 创建订单
			order = new Order().setOrderSn(orderToken).setStatus(OrderStatusEnum.PENDING_PAYMENT.getValue())
					.setSourceType(OrderTypeEnum.APP.getValue()).setMemberId(SecurityUtil.getUser().getUserId())
					.setRemark(orderSubmitDto.getRemark()).setPayAmount(orderSubmitDto.getPayAmount())
					.setTotalQuantity(orderItems.stream().map(OrderItemDto::getCount).reduce(0, Integer::sum))
					.setTotalAmount(
							orderItems.stream().map(item -> item.getPrice() * item.getCount()).reduce(0L, Long::sum));

			boolean result = this.save(order);

			// 添加订单明细
			if (result) {
				List<Item> saveOrderItems = orderItems.stream().map(orderFormItem -> {
					Item orderItem = new Item();
					BeanUtil.copyProperties(orderFormItem, orderItem);
					orderItem.setOrderId(order.getId());
					orderItem.setTotalAmount(orderFormItem.getPrice() * orderFormItem.getCount());
					return orderItem;
				}).collect(Collectors.toList());
				result = orderItemService.saveBatch(saveOrderItems);
				if (result) {
					// todo 订单超时取消
				}
			}
			Assert.isTrue(result, "订单提交失败");
		}
		catch (Exception e) {
			redisTemplate.opsForValue().set(OrderConstants.ORDER_TOKEN_PREFIX + orderToken, orderToken);
			throw new FxzException(e.getMessage());
		}

		// 成功响应返回值构建
		OrderSubmitVo submitVO = new OrderSubmitVo();
		submitVO.setOrderId(order.getId());
		submitVO.setOrderSn(order.getOrderSn());
		return submitVO;
	}

	/**
	 * 锁定商品库存
	 * @param orderToken
	 * @param orderItems
	 */
	private void lockStock(String orderToken, List<OrderItemDto> orderItems) {
		LockStockDTO lockStockDTO = new LockStockDTO();

		lockStockDTO.setOrderToken(orderToken);

		List<LockStockDTO.LockedSku> lockedSkuList = orderItems.stream().map(
				orderItem -> new LockStockDTO.LockedSku().setSkuId(orderItem.getSkuId()).setCount(orderItem.getCount()))
				.collect(Collectors.toList());
		lockStockDTO.setLockedSkuList(lockedSkuList);

		remoteSkuService.lockStock(lockStockDTO);
	}

	/**
	 * 订单验价，进入结算页面的订单总价和当前所有商品的总价是否一致
	 * @param orderTotalAmount 订单总金额
	 * @param orderItems 订单商品明细
	 * @return true：订单总价和商品总价一致；false：订单总价和商品总价不一致。
	 */
	private boolean checkOrderPrice(Long orderTotalAmount, List<OrderItemDto> orderItems) {
		CheckPriceDTO checkPriceDTO = new CheckPriceDTO();

		List<CheckPriceDTO.CheckSku> checkSkus = orderItems.stream().map(orderFormItem -> {
			CheckPriceDTO.CheckSku checkSku = new CheckPriceDTO.CheckSku();
			checkSku.setSkuId(orderFormItem.getSkuId());
			checkSku.setCount(orderFormItem.getCount());
			return checkSku;
		}).collect(Collectors.toList());

		// 订单总金额
		checkPriceDTO.setOrderTotalAmount(orderTotalAmount);
		// 订单的商品明细
		checkPriceDTO.setCheckSkus(checkSkus);

		// 调用验价接口，比较订单总金额和商品明细总金额，不一致则说明商品价格变动
		Result<Boolean> checkPriceResult = remoteSkuService.checkPrice(checkPriceDTO);

		return Result.isSuccess(checkPriceResult) && Boolean.TRUE.equals(checkPriceResult.getData());
	}

	/**
	 * 获取订单的商品明细信息
	 * <p>
	 * 创建订单两种方式，1：直接购买；2：购物车结算
	 * @param skuId 直接购买必有值，购物车结算必没值
	 * @return 商品项
	 */
	private List<OrderItemDto> getOrderItems(Long skuId) {
		List<OrderItemDto> orderItems = new ArrayList<>();

		// 直接购买
		if (skuId != null) {
			SkuInfoDTO skuInfoDTO = remoteSkuService.getSkuInfo(skuId).getData();

			OrderItemDto orderItemDTO = new OrderItemDto();
			BeanUtil.copyProperties(skuInfoDTO, orderItemDTO);

			// 直接购买商品的数量为1
			orderItemDTO.setCount(1);
			orderItems.add(orderItemDTO);
		}
		else {
			// todo 购物车结算
		}

		return orderItems;
	}

}
