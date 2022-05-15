package com.fxz.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.order.dto.OrderSubmitDto;
import com.fxz.mall.order.entity.Order;
import com.fxz.mall.order.enums.PayTypeEnum;
import com.fxz.mall.order.vo.OrderConfirmVo;
import com.fxz.mall.order.vo.OrderSubmitVo;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-15
 */
public interface OrderService extends IService<Order> {

	/**
	 * 订单确认 → 进入创建订单页面
	 * <p>
	 * 获取购买商品明细、用户默认收货地址、防重提交唯一token 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
	 * @param skuId 直接购买必填，购物车结算不填
	 * @return OrderConfirmVO
	 */
	OrderConfirmVo confirm(Long skuId);

	/**
	 * 订单提交
	 */
	OrderSubmitVo submit(OrderSubmitDto orderSubmitDto);

	/**
	 * 订单支付
	 * @param orderId 订单id
	 * @param payTypeEnum 支付方式
	 * @param appId 小程序appId
	 */
	<T> T pay(Long orderId, String appId, PayTypeEnum payTypeEnum);

}