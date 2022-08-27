package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 促销活动使用的范围
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 13:32
 */
@AllArgsConstructor
public enum PromotionsScopeTypeEnum implements IBaseEnum<String> {

	/**
	 * 全品类
	 */
	ALL("all", "全品类"),

	/**
	 * 部分商品分类
	 */
	PORTION_GOODS_CATEGORY("portion_goods_category", "部分商品分类"),

	/**
	 * 指定商品
	 */
	PORTION_GOODS("portion_goods", "指定商品");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
