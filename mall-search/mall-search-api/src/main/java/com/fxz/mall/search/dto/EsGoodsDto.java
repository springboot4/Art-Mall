package com.fxz.mall.search.dto;

import com.fxz.mall.product.dto.AttributeValueDto;
import com.fxz.mall.product.dto.SkuDto;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/29 12:26
 */
@Data
@FieldNameConstants
public class EsGoodsDto {

	private Long id;

	private String name;

	private Long categoryId;

	private String categoryName;

	private Long brandId;

	private String brandName;

	private Long originPrice;

	private Long price;

	private String picUrl;

	private String[] subPicUrls;

	private String description;

	private String detail;

	private List<AttributeValueDto> attrList;

	private List<SkuDto> skuList;

}
