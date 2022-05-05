package com.fxz.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
@Data
@TableName("category")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 商品分类名称
	 */
	private String name;

	/**
	 * 父级ID
	 */
	private Long parentId;

	/**
	 * 层级
	 */
	private Integer level;

	/**
	 * 图标地址
	 */
	private String iconUrl;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 显示状态:( 0:隐藏 1:显示)
	 */
	private Integer visible;

}
