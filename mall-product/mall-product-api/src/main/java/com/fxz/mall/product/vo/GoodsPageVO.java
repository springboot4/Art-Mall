package com.fxz.mall.product.vo;

import lombok.Data;

/**
 * 商品分页对象
 *
 * @author fxz
 */
@Data
public class GoodsPageVO {

	/**
	 * 商品ID
	 */
	private Long id;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品价格(单位：分)
	 */
	private Long price;

	/**
	 * 销量
	 */
	private Integer sales;

	/**
	 * 图片地址
	 */
	private String picUrl;

}
