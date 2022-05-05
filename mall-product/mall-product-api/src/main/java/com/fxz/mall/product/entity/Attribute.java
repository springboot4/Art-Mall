package com.fxz.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品属性表
 *
 * @author fxz
 * @date 2022-05-05
 */
@Data
@TableName("attribute")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Attribute extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 分类ID
	 */
	private Long categoryId;

	/**
	 * 属性名称
	 */
	private String name;

	/**
	 * 类型(1:规格;2:属性;)
	 */
	private Integer type;

}
