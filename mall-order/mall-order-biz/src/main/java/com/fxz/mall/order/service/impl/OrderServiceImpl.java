package com.fxz.mall.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.enums.BusinessTypeEnum;
import com.fxz.common.redis.util.BusinessNoGenerator;
import com.fxz.mall.order.constant.OrderConstants;
import com.fxz.mall.order.dto.OrderDto;
import com.fxz.mall.order.dto.OrderItemDto;
import com.fxz.mall.order.entity.Order;
import com.fxz.mall.order.mapper.OrderMapper;
import com.fxz.mall.order.service.OrderService;
import com.fxz.mall.order.vo.OrderConfirmVO;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.fxz.mall.user.dto.AddressDto;
import com.fxz.mall.user.feign.RemoteAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-14
 */
@SuppressWarnings("all")
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	private final OrderMapper orderMapper;

	private final ThreadPoolExecutor threadPoolExecutor;

	private final RemoteSkuService remoteSkuService;

	private final RemoteAddressService remoteAddressService;

	private final BusinessNoGenerator businessNoGenerator;

	private final RedisTemplate redisTemplate;

	/**
	 * 添加
	 */
	@Override
	public Boolean addOrder(OrderDto orderDto) {
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		orderMapper.insert(order);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateOrder(OrderDto orderDto) {
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		orderMapper.updateById(order);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Order> pageOrder(Page<Order> pageParam, Order order) {
		return orderMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Order findById(Long id) {
		return orderMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Order> findAll() {
		return orderMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteOrder(Long id) {
		orderMapper.deleteById(id);
		return Boolean.TRUE;
	}

	/**
	 * todo feign异步线程丢失请求头 订单确认 → 进入创建订单页面
	 * <p>
	 * 获取购买商品明细、用户默认收货地址、防重提交唯一token 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
	 * @param skuId 直接购买必填，购物车结算不填
	 * @return OrderConfirmVO
	 */
	@Override
	public OrderConfirmVO confirm(Long skuId) {
		OrderConfirmVO orderConfirmVO = new OrderConfirmVO();

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

		// 生成唯一token，防止订单重复提交
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