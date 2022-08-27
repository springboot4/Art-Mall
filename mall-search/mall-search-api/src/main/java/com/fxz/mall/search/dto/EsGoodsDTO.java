package com.fxz.mall.search.dto;

import com.fxz.mall.product.dto.AttributeValueDTO;
import com.fxz.mall.product.dto.SkuDTO;
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
public class EsGoodsDTO {

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

	private List<AttributeValueDTO> attrList;

	private List<SkuDTO> skuList;

}
