package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.vo.CouponVO;

import java.time.LocalDateTime;

/**
 * 优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface CouponService extends IService<Coupon> {

	/**
	 * 新增优惠券
	 */
	Boolean addCoupon(CouponVO couponVO);

	/**
	 * 检验优惠券促销时间合法性
	 */
	void checkPromotionsTime(LocalDateTime startTime, LocalDateTime endTime);

	/**
	 * 检验优惠券基础信息合法性
	 */
	void checkCouponInfo(Coupon coupon);

	/**
	 * 检验优惠券促销范围合法性
	 */
	void checkCouponScope(CouponVO coupon);

	/**
	 * 检验指定商品信息
	 */
	void checkCouponPortionGoods(CouponVO coupon);

	/**
	 * 更新促销商品信息
	 */
	void updatePromotionsGoods(CouponVO couponVO);

}