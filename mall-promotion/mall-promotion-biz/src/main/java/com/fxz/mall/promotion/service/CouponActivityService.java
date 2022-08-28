package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.entity.CouponActivity;
import com.fxz.mall.promotion.entity.CouponActivityItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 优惠券活动
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface CouponActivityService extends IService<CouponActivity> {

	/**
	 * 添加优惠券活动
	 * @param couponActivity 优惠券活动
	 */
	void saveCouponActivity(CouponActivityDTO couponActivity);

	/**
	 * 初始化优惠券活动
	 * @param couponActivity 优惠券活动
	 */
	void initCouponActivity(CouponActivityDTO couponActivity);

	/**
	 * 检验优惠券活动的合法性
	 * @param couponActivity 优惠券活动
	 */
	void checkCouponActivity(CouponActivityDTO couponActivity);

	/**
	 * 检验优惠券活动时间的合法性
	 * @param startTime 活动开始时间
	 * @param endTime 活动结束时间
	 */
	void checkPromotionsTime(LocalDateTime startTime, LocalDateTime endTime);

	/**
	 * 检验优惠券项
	 * @param couponActivityItems 优惠券项
	 */
	void checkCouponActivityItem(List<CouponActivityItem> couponActivityItems);

	/**
	 * 保存优惠券活动项
	 * @param couponActivity 优惠券活动
	 */
	void updatePromotionsGoods(CouponActivityDTO couponActivity);

	/**
	 * 根据活动id发放优惠券
	 * @param id 优惠券活动
	 */
	void activitySend(Long id);

	/**
	 * 根据优惠券活动信息获取会员信息
	 * @param activity 优惠券活动信息
	 * @return 会员信息
	 */
	List<Map<String, Object>> getMemberList(CouponActivity activity);

	/**
	 * 关闭券活动
	 * @param id 券活动id
	 */
	void closeCouponActivity(Long id);

}