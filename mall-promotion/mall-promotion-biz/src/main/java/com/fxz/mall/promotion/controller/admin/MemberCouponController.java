package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@RestController
@RequestMapping("/admin/memberCoupon")
@RequiredArgsConstructor
public class MemberCouponController {

	private final MemberCouponService memberCouponService;

	/**
	 * 会员领取优惠券
	 * @param couponId 优惠券id
	 */
	@GetMapping("receiveCoupon/{couponId}")
	public Result<Boolean> receiveCoupon(@PathVariable("couponId") Long couponId) {
		return Result.judge(memberCouponService.receiveCoupon(couponId));
	}

}