package com.fxz.mall.search.entity;

import com.fxz.common.canal.annotation.CanalModel;
import com.fxz.common.canal.common.FieldNamingPolicy;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品
 *
 * @author fxz
 * @date 2022-05-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@CanalModel(database = "fxz_mall_product", table = "spu", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
public class EsSpu extends BaseEntity {

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
