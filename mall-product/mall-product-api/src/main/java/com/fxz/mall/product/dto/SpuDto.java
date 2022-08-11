package com.fxz.mall.product.dto;

import com.fxz.common.core.serializer.ImgUrl;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品表
 *
 * @author fxz
 * @date 2022-05-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SpuDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String name;

	private Long categoryId;

	private Long brandId;

	private Long originPrice;

	private Long price;

	private Integer sales;

	private String picUrl;

	@ImgUrl
	private String album;

	private String unit;

	private String description;

	private String detail;

	private Integer status;

}