package com.fxz.mall.order.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fxz
 */

@AllArgsConstructor
public enum OrderTypeEnum implements IBaseEnum<Integer> {

	/**
	 * pc订单
	 */
	WEB(0, "PC"),

	/**
	 * app订单
	 */
	APP(1, "APP");

	@Getter
	private final Integer value;

	@Getter
	private final String label;

}