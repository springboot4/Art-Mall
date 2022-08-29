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
	 * 关闭优惠券
	 * @param id 优惠券id
	 */
	Boolean closeCoupon(Long id);

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
	void updatePromotionsGoods(Coupon coupon);

	/**
	 * 更新优惠券的状态
	 * @param id 优惠券id
	 * @param startTime 活动开始时间
	 * @param endTime 活动结束时间
	 */
	Boolean updateStatus(Long id, LocalDateTime startTime, LocalDateTime endTime);

	/**
	 * 查看优惠券信息
	 * @param id 优惠券id
	 * @return 优惠券视图对象
	 */
	CouponVO couponInfo(Long id);

	/**
	 * 更新优惠券
	 * @param couponVO 优惠券信息
	 */
	Boolean updateCoupon(CouponVO couponVO);

	void checkStatus(Long id);

	/**
	 * 会员领取优惠券
	 * @param couponId 优惠券id
	 */
	Boolean memberReceiveCoupon(Long couponId);

	/**
	 * 领取优惠券
	 * @param couponId 优惠券id
	 * @param receiveNum 领取数量
	 */
	void receiveCoupon(Long couponId, Integer receiveNum);

	/**
	 * 删除优惠券
	 * @param id 优惠券id
	 * @return {@code Boolean}
	 */
	Boolean removeCoupon(Long id);

	/**
	 * 检查优惠券领取数量
	 * @param couponId 优惠券id
	 * @param memberId 会员id
	 */
	void checkCouponLimit(Long couponId, Long memberId);

}