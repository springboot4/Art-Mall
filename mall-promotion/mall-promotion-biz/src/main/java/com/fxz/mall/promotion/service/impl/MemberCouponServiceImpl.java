package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.entity.MemberCoupon;
import com.fxz.mall.promotion.enums.CouponGetEnum;
import com.fxz.mall.promotion.enums.MemberCouponStatusEnum;
import com.fxz.mall.promotion.enums.PromotionsStatusEnum;
import com.fxz.mall.promotion.mapper.MemberCouponMapper;
import com.fxz.mall.promotion.service.CouponService;
import com.fxz.mall.promotion.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCouponServiceImpl extends ServiceImpl<MemberCouponMapper, MemberCoupon>
		implements MemberCouponService {

	private final CouponService couponService;

	/**
	 * 会员领取优惠券
	 * @param couponId 优惠券id
	 */
	@Override
	public Boolean receiveCoupon(Long couponId) {
		Coupon coupon = couponService.getById(couponId);
		if (Objects.isNull(coupon) || CouponGetEnum.FREE.getValue().equals(coupon.getGetType())) {
			throw new FxzException("优惠券信息不存在！");
		}

		FxzAuthUser user = SecurityUtil.getUser();
		this.checkCouponLimit(couponId, user.getUserId());

		MemberCoupon memberCoupon = new MemberCoupon(coupon);
		memberCoupon.setMemberId(user.getUserId());
		memberCoupon.setMemberName(user.getUsername());
		memberCoupon.setMemberCouponStatus(MemberCouponStatusEnum.RECEIVE.getValue());
		this.save(memberCoupon);

		couponService.receiveCoupon(couponId, 1);

		return Boolean.TRUE;
	}

	public void checkCouponLimit(Long couponId, Long memberId) {
		Coupon coupon = couponService.getById(couponId);
		LambdaQueryWrapper<MemberCoupon> queryWrapper = new LambdaQueryWrapper<MemberCoupon>()
				.eq(MemberCoupon::getCouponId, couponId).eq(MemberCoupon::getMemberId, memberId);
		long haveCoupons = this.count(queryWrapper);
		if (!PromotionsStatusEnum.START.getValue().equals(coupon.getPromotionStatus())) {
			throw new FxzException("优惠券已过期！");
		}
		if (coupon.getPublishNum() != 0 && coupon.getReceivedNum() >= coupon.getPublishNum()) {
			throw new FxzException("优惠券剩余领取数量不足！");
		}
		if (!coupon.getCouponLimitNum().equals(0) && haveCoupons >= coupon.getCouponLimitNum()) {
			throw new FxzException("此优惠券最多领取" + coupon.getCouponLimitNum() + "张");
		}
	}

}