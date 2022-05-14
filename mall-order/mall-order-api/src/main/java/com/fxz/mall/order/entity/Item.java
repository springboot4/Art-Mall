package com.fxz.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单商品信息表
 *
 * @author fxz
 * @date 2022-05-14
 */
@Data
@TableName("order_item")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 订单ID
	 */
	private Long orderId;

	/**
	 * 商品名称
	 */
	private String spuName;

	/**
	 * 商品ID
	 */
	private Long skuId;

	/**
	 * 商品编码
	 */
	private String skuSn;

	/**
	 * 规格名称
	 */
	private String skuName;

	/**
	 * 商品图片
	 */
	private String picUrl;

	/**
	 * 商品单价(单位：分)
	 */
	private Long price;

	/**
	 * 商品数量
	 */
	private Integer count;

	/**
	 * 商品总价(单位：分)
	 */
	private Long totalAmount;

	/**
	 * 逻辑删除标识(1:已删除；0:正常)
	 */
	private Integer deleted;

}
