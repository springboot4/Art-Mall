package com.fxz.mall.product.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品品牌表
 *
 * @author fxz
 * @date 2022-05-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BrandDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String name;

	private String logoUrl;

	private Integer sort;

}