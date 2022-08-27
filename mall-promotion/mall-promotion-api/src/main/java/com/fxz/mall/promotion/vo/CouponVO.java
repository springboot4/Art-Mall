package com.fxz.mall.promotion.vo;

import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.entity.PromotionGoods;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 优惠券视图对象
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CouponVO extends Coupon {

	/**
	 * 如果优惠券的使用范围是指定商品的话，则该属性不为null
	 */
	private List<PromotionGoods> promotionGoodsList;

}
