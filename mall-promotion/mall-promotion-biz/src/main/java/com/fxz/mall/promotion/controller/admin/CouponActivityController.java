package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.service.CouponActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠券活动
 *
 * @author fxz
 * @date 2022-08-27
 */
@RestController
@RequestMapping("/admin/couponActivity")
@RequiredArgsConstructor
public class CouponActivityController {

	private final CouponActivityService couponActivityService;

	/**
	 * 添加优惠券活动
	 * @param couponActivity 优惠券活动
	 */
	@Ojbk
	@PostMapping(value = "/save")
	public Result<Void> saveCoupon(@RequestBody CouponActivityDTO couponActivity) {
		couponActivityService.saveCouponActivity(couponActivity);
		return Result.success();
	}

}