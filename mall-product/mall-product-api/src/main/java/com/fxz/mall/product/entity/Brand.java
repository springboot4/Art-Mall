package com.fxz.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@Data
@TableName("brand")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 品牌名称
	 */
	private String name;

	/**
	 * LOGO图片
	 */
	private String logoUrl;

	/**
	 * 排序
	 */
	private Integer sort;

}
