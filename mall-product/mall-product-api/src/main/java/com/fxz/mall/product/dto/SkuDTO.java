package com.fxz.mall.product.dto;

import com.fxz.common.mp.base.BaseEntity;
import com.fxz.mall.product.dto.AttributeValueDTO;
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
public class SkuDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String skuSn;

	private Long spuId;

	private String name;

	private Long price;

	private Integer stockNum;

	private Integer lockedStockNum;

	private String picUrl;

	private List<AttributeValueDTO> specValList;

	/**
	 * 商品促销活动json
	 */
	private String promotionMapJson;

}