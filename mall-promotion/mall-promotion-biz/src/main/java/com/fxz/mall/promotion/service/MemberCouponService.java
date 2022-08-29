package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.MemberCoupon;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface MemberCouponService extends IService<MemberCoupon> {

	/**
	 * 关闭会员优惠券
	 * @param id 优惠券id
	 */
	void closeCoupon(Long id);

}