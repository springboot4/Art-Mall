package com.fxz.mall.promotion.vo;

import com.fxz.common.core.serializer.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 秒杀活动商品视图对象
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/10 16:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeckillGoodsVO implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 活动id
	 */
	private Long seckillId;

	/**
	 * 时刻
	 */
	private Integer timeLine;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * skuID
	 */
	private Long skuId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品图片
	 */
	@ImgUrl
	private String goodsImage;

	/**
	 * 价格
	 */
	private Long price;

	/**
	 * 促销数量
	 */
	private Integer quantity;

	/**
	 * 已售数量
	 */
	private Integer salesNum;

	/**
	 * 商品原始价格
	 */
	private Long originalPrice;

}
