package com.fxz.mall.product.dto;

import com.fxz.mall.product.entity.Sku;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SkuDTO extends Sku {

	private static final long serialVersionUID = -1L;

	/**
	 * 原始价格
	 */
	private Long originPrice;

	/**
	 * 是否优惠
	 */
	private boolean isPromotion;

	/**
	 * 属性值
	 */
	private List<AttributeValueDTO> specValList;

	/**
	 * 商品促销活动json
	 */
	private String promotionMapJson;

}