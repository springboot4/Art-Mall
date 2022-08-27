package com.fxz.mall.product.vo;

import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.entity.Spu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 商品信息
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/9 09:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsVO extends Spu {

	/**
	 * 商品分类名称
	 */
	private String categoryName;

	/**
	 * 品牌
	 */
	private String brandName;

	/**
	 * sku列表
	 */
	private List<Sku> skuList;

}
