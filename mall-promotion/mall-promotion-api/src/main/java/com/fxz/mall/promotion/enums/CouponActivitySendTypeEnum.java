package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券发送范围
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 13:16
 */
@AllArgsConstructor
public enum CouponActivitySendTypeEnum implements IBaseEnum<String> {

	/**
	 * 全部会员
	 */
	ALL("all", "全部会员"),

	/**
	 * 指定会员
	 */
	DESIGNATED("designated", "指定会员");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
