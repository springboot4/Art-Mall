package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券使用时间类型
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 13:09
 */
@AllArgsConstructor
public enum CouponRangeDayEnum implements IBaseEnum<String> {

	/**
	 * 固定时间
	 */
	FIXEDTIME("fixedtime", "固定时间"),

	/**
	 * 动态时间
	 */
	DYNAMICTIME("dynamictime", "动态时间");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
