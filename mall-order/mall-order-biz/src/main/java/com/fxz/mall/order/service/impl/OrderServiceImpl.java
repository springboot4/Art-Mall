package com.fxz.mall.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.enums.BusinessTypeEnum;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.mp.result.Result;
import com.fxz.common.redis.util.BusinessNoGenerator;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.order.constant.OrderConstants;
import com.fxz.mall.order.dto.CartItemDTO;
import com.fxz.mall.order.dto.OrderItemDto;
import com.fxz.mall.order.dto.OrderSubmitDto;
import com.fxz.mall.order.entity.Order;
import com.fxz.mall.order.entity.OrderItem;
import com.fxz.mall.order.enums.OrderStatusEnum;
import com.fxz.mall.order.enums.OrderTypeEnum;
import com.fxz.mall.order.enums.PayTypeEnum;
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
import com.fxz.mall.user.feign.RemoteMemberService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
@SuppressWarnings("all")
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderOrderItemServiceImpl orderItemService;

    private final ThreadPoolExecutor threadPoolExecutor;

    private final RemoteSkuService remoteSkuService;

    private final RemoteAddressService remoteAddressService;

    private final RemoteMemberService remoteMemberService;

    private final BusinessNoGenerator businessNoGenerator;

    private final RedisTemplate redisTemplate;

    private final RedissonClient redissonClient;

    private final RabbitTemplate rabbitTemplate;

    private final CartServiceImpl cartService;

    /**
     * 获取购买商品明细、用户默认收货地址、防重提交唯一token
     * <p/>
     * 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
     *
     * @param skuId 直接购买必填，购物车结算不填
     * @return OrderConfirmVO
     */
    @Override
    public OrderConfirmVo confirm(Long skuId) {
        OrderConfirmVo orderConfirmVO = new OrderConfirmVo();
        Long memberId = SecurityUtil.getUser().getUserId();

        // 获取订单的商品明细信息
        CompletableFuture<Void> orderItemsCompletableFuture = CompletableFuture.runAsync(() -> {
            List<OrderItemDto> orderItems = this.getOrderItems(skuId, memberId);
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
     * 订单提交
     */
    @SneakyThrows
    @GlobalTransactional(rollbackFor = Exception.class)
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
                List<OrderItem> saveOrderOrderItems = orderItems.stream().map(orderFormItem -> {
                    OrderItem orderItem = new OrderItem();
                    BeanUtil.copyProperties(orderFormItem, orderItem);
                    orderItem.setOrderId(order.getId());
                    orderItem.setTotalAmount(orderFormItem.getPrice() * orderFormItem.getCount());
                    return orderItem;
                }).collect(Collectors.toList());
                result = orderItemService.saveBatch(saveOrderOrderItems);
                if (result) {
                    // 通过死信队列实现订单超时取消
                    log.info("发送延时消息:{}", orderToken);
                    rabbitTemplate.convertAndSend("order.exchange", "order.create.routing.key", orderToken);
                }
            }
            Assert.isTrue(result, "订单提交失败");
        } catch (Exception e) {
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
     * 订单支付 todo 微信支付
     *
     * @param orderId     订单id
     * @param payTypeEnum 支付方式
     * @param appId       小程序appId
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public <T> T pay(Long orderId, String appId, PayTypeEnum payTypeEnum) {
        Order order = this.getById(orderId);

        Assert.isTrue(order != null, "订单不存在");
        Assert.isTrue(OrderStatusEnum.PENDING_PAYMENT.getValue().equals(order.getStatus()), "订单不可支付，请检查订单状态");

        RLock lock = redissonClient.getLock(OrderConstants.ORDER_SN_PREFIX + order.getOrderSn());
        try {

            lock.lock();
            T result = null;
            switch (payTypeEnum) {
                case WX_JSAPI:
                    // result = (T) wxJsapiPay(appId, order);
                    break;
                default:
                    result = (T) balancePay(order);
                    break;
            }

            // 扣减库存
            Result<?> deductStockResult = remoteSkuService.deductStock(order.getOrderSn());
            Assert.isTrue(Result.isSuccess(deductStockResult), "扣减商品库存失败");
            return result;
        } finally {
            // 释放锁
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    /**
     * 超时未支付关闭订单
     *
     * @param orderToken 订单号
     * @return 是否关闭成功
     */
    @Override
    public Boolean closeOrder(String orderToken) {
        log.info("订单超时取消，orderToken:{}", orderToken);

        // 根据订单号查询订单信息
        Order order = this.getOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn, orderToken));
        if (order == null || !OrderStatusEnum.PENDING_PAYMENT.getValue().equals(order.getStatus())) {
            return false;
        }

        // 设置订单状态
        order.setStatus(OrderStatusEnum.AUTO_CANCEL.getValue());

        return this.updateById(order);
    }

    /**
     * 余额支付
     *
     * @param order 订单
     * @return 是否支付成功
     */
    private Boolean balancePay(Order order) {
        // 扣减余额
        Long payAmount = order.getPayAmount();
        Result<?> deductBalanceResult = remoteMemberService.deductBalance(payAmount);
        Assert.isTrue(Result.isSuccess(deductBalanceResult), "扣减账户余额失败");

        // 更新订单状态
        order.setStatus(OrderStatusEnum.PAYED.getValue());
        order.setPayType(PayTypeEnum.BALANCE.getValue());
        order.setPayTime(LocalDateTime.now());
        this.updateById(order);
        // todo 支付成功删除购物车已勾选的商品

        return Boolean.TRUE;
    }

    /**
     * 锁定商品库存
     *
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
     *
     * @param orderTotalAmount 订单总金额
     * @param orderItems       订单商品明细
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
     *
     * @param skuId 直接购买必有值，购物车结算必没值
     * @return 商品项
     */
    private List<OrderItemDto> getOrderItems(Long skuId, Long memberId) {
        List<OrderItemDto> orderItems = new ArrayList<>();

        // 直接购买
        if (skuId != null) {
            SkuInfoDTO skuInfoDTO = remoteSkuService.getSkuInfo(skuId).getData();

            OrderItemDto orderItemDTO = new OrderItemDto();
            BeanUtil.copyProperties(skuInfoDTO, orderItemDTO);

            // 直接购买商品的数量为1
            orderItemDTO.setCount(1);
            orderItems.add(orderItemDTO);
        } else {
            List<CartItemDTO> cartItems = cartService.listCartItemByMemberId(memberId);
            orderItems = cartItems.stream()
                    .filter(CartItemDTO::getChecked)
                    .map(cartItem -> {
                        OrderItemDto orderItemDTO = new OrderItemDto();
                        BeanUtil.copyProperties(cartItem, orderItemDTO);
                        return orderItemDTO;
                    })
                    .collect(Collectors.toList());
        }

        return orderItems;
    }

}
