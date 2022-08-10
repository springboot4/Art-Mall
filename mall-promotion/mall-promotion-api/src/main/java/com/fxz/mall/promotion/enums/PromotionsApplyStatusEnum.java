package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 促销申请状态枚举
 * @author Fxz
 * @version 1.0
 * @date 2022/8/10 11:32
 */
@AllArgsConstructor
public enum PromotionsApplyStatusEnum implements IBaseEnum<String> {

	/**
	 * 申请状态
	 */
	APPLY("apply", "申请"),
	/**
	 * 通过状态
	 */
	PASS("pass", "通过"),
	/**
	 * 拒绝状态
	 */
	REFUSE("pass", "拒绝");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
