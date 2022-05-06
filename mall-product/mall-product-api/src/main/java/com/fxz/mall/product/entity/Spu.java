package com.fxz.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@Data
@TableName("spu")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Spu extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品类型ID
	 */
	private Long categoryId;

	/**
	 * 商品品牌ID
	 */
	private Long brandId;

	/**
	 * 原价【起】
	 */
	private Long originPrice;

	/**
	 * 现价【起】
	 */
	private Long price;

	/**
	 * 销量
	 */
	private Integer sales;

	/**
	 * 商品主图
	 */
	private String picUrl;

	/**
	 * 商品图册
	 */
	private String album;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 商品简介
	 */
	private String description;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 商品状态：0-下架 1-上架
	 */
	private Integer status;

}
