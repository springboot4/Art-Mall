package com.fxz.mall.product.dto;

import com.fxz.common.mp.base.BaseEntity;
import com.fxz.mall.product.entity.Attribute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 商品属性表
 *
 * @author fxz
 * @date 2022-05-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AttributeDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long categoryId;

	private String name;

	private Integer type;

	@NotEmpty
	private List<Attribute> attributes;

}