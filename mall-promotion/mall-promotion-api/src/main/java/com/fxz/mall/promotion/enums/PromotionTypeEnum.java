package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 促销活动类型枚举
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/9 22:52
 */
@AllArgsConstructor
public enum PromotionTypeEnum implements IBaseEnum<String> {

	/**
	 * 秒杀
	 */
	SECKILL("seckill", "秒杀"),

	/**
	 * 优惠券
	 */
	COUPON("coupon", "优惠券");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
