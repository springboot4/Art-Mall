package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.CouponActivityItem;

import java.util.List;

/**
 * 优惠券活动项
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface CouponActivityItemService extends IService<CouponActivityItem> {

	/**
	 * 根据优惠券活动id获取优惠券项
	 * @param id 优惠券活动id
	 */
	List<CouponActivityItem> getCouponActivityList(Long id);

	/**
	 * 根据优惠券id删除优惠券活动项
	 * @param id 优惠券id
	 */
	void removeByCouponId(Long id);

}