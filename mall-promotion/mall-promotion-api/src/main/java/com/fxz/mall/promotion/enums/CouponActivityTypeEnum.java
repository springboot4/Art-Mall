package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券活动类型
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 13:24
 */
@AllArgsConstructor
public enum CouponActivityTypeEnum implements IBaseEnum<String> {

	/**
	 * 新人赠券
	 */
	REGISTERED("registered", "新人赠券"),

	/**
	 * 精确发券
	 */
	SPECIFY("specify", "精确发券");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
