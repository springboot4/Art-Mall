package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券的获取方式枚举
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 13:04
 */
@AllArgsConstructor
public enum CouponGetEnum implements IBaseEnum<String> {

	/**
	 * 优惠券的获取方式:直接领取
	 */
	FREE("free", "直接领取"),

	/**
	 * 优惠券的获取方式:活动获取
	 */
	ACTIVITY("activity", "活动获取");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
