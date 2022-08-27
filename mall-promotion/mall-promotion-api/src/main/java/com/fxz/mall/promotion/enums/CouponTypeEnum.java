package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券减免类型枚举
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 12:55
 */
@AllArgsConstructor
public enum CouponTypeEnum implements IBaseEnum<String> {

	/**
	 * 优惠券减免类型为:折扣
	 */
	DISCOUNT("discount", "折扣"),

	/**
	 * 优惠券减免类型为:减免现金
	 */
	CASH("cash", "减免现金");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
