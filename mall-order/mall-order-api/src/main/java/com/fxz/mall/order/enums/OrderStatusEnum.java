package com.fxz.mall.order.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fxz
 */

@AllArgsConstructor
public enum OrderStatusEnum implements IBaseEnum<Integer> {

	/**
	 * 待支付
	 */
	PENDING_PAYMENT(101, "待支付"),
	/**
	 * 用户取消
	 */
	USER_CANCEL(102, "用户取消"),
	/**
	 * 系统自动取消
	 */
	AUTO_CANCEL(103, "系统自动取消"),

	/**
	 * 已支付
	 */
	PAYED(201, "已支付"),
	/**
	 * 申请退款
	 */
	APPLY_REFUND(202, "申请退款"),
	/**
	 * 已退款
	 */
	REFUNDED(203, "已退款"),

	/**
	 * 已发货
	 */
	DELIVERED(301, "已发货"),

	/**
	 * 用户收货
	 */
	USER_RECEIVE(401, "用户收货"),
	/**
	 * 系统自动收货
	 */
	AUTO_RECEIVE(402, "系统自动收货"),

	/**
	 * 已完成
	 */
	FINISHED(901, "已完成");

	@Getter
	private final Integer value;

	@Getter
	private final String label;

}