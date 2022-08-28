package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.service.CouponService;
import com.fxz.mall.promotion.vo.CouponVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@RestController
@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
public class CouponController {

	private final CouponService couponService;

	/**
	 * 新增优惠券
	 */
	@Ojbk
	@PostMapping(value = "/save")
	public Result<Boolean> saveCoupon(@RequestBody CouponVO couponVO) {
		return Result.judge(couponService.addCoupon(couponVO));
	}

	/**
	 * 关闭优惠券
	 * @param id 优惠券id
	 */
	@Ojbk
	@PutMapping(value = "/close/{id}")
	public Result<Boolean> closeCoupon(@PathVariable("id") Long id) {
		return Result.judge(couponService.closeCoupon(id));
	}

}