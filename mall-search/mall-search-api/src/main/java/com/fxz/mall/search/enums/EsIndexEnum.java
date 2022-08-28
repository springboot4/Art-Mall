package com.fxz.mall.search.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/28 10:34
 */
@AllArgsConstructor
public enum EsIndexEnum implements IBaseEnum<String> {

	/**
	 * 商品索引
	 */
	PRODUCT("product", "商品索引");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
