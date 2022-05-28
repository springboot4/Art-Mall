package com.fxz.mall.product.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品属性项表
 *
 * @author fxz
 * @date 2022-05-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AttributeValueDto extends BaseEntity {

	private Long id;

	private Long spuId;

	private Long attributeId;

	private String name;

	private String value;

	private Integer type;

	private String picUrl;

}