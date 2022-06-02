package com.fxz.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.core.serializer.ImgUrl;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Data
@TableName("sku")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Sku extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 *
	 */
	private Long id;

	/**
	 * 商品编码
	 */
	private String skuSn;

	/**
	 * SPU ID
	 */
	private Long spuId;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品价格(单位：分)
	 */
	private Long price;

	/**
	 * 库存数量
	 */
	private Integer stockNum;

	/**
	 * 锁定库存数量
	 */
	private Integer lockedStockNum;

	/**
	 * 商品图片地址
	 */
	@ImgUrl
	private String picUrl;

	@TableField(exist = false)
	private String specIds;

}
