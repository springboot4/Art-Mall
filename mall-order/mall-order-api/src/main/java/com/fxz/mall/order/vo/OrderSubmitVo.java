package com.fxz.mall.order.vo;

import lombok.Data;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/15 09:19
 */
@Data
public class OrderSubmitVo {

	/**
	 * 订单ID
	 */
	private Long orderId;

	/**
	 * 订单编号，进入支付页面显示
	 */
	private String orderSn;

}
