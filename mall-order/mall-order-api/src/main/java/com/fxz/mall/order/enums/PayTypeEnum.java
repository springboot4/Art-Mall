package com.fxz.mall.order.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fxz
 */

@AllArgsConstructor
public enum PayTypeEnum implements IBaseEnum<Integer> {

	/**
	 * 微信JSAPI支付
	 */
	WX_JSAPI(1, "微信JSAPI支付"),
	/**
	 * 支付宝支付
	 */
	ALIPAY(2, "支付宝支付"),
	/**
	 * 会员余额支付
	 */
	BALANCE(3, "会员余额支付"),
	/**
	 * 微信APP支付
	 */
	WX_APP(4, "微信APP支付");

	@Getter
	private Integer value;

	@Getter
	private String label;

}