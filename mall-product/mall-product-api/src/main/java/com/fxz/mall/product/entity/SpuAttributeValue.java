package com.fxz.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品属性项表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Data
@TableName("spu_attribute_value")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SpuAttributeValue extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 产品ID
	 */
	private Long spuId;

	/**
	 * 属性ID
	 */
	private Long attributeId;

	/**
	 * 属性名称
	 */
	private String name;

	/**
	 * 属性值
	 */
	private String value;

	/**
	 * 类型(1:规格;2:属性;)
	 */
	private Integer type;

	/**
	 * 规格图片
	 */
	private String picUrl;

}
