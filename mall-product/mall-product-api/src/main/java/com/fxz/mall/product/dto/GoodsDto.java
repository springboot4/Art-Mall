package com.fxz.mall.product.dto;

import lombok.Data;

import java.util.List;

/**
 * @author fxz
 */
@Data
public class GoodsDto {

	private Long id;

	private String name;

	private Long categoryId;

	private Long brandId;

	private Long originPrice;

	private Long price;

	private String picUrl;

	private String[] subPicUrls;

	private String description;

	private String detail;

	private List<AttributeValueDto> attrList;

	private List<SkuDto> skuList;

}
