package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会员优惠券的状态
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/28 20:18
 */
@AllArgsConstructor
public enum MemberCouponStatusEnum implements IBaseEnum<String> {

	/**
	 * 领取
	 */
	RECEIVE("receive", "领取"),

	/**
	 * 已使用
	 */
	USED("used", "已使用"),

	/**
	 * 过期
	 */
	EXPIRE("expire", "过期"),

	/**
	 * 作废
	 */
	CLOSED("closed", "作废");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
