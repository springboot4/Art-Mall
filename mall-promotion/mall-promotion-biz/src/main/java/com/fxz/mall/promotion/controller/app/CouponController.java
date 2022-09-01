package com.fxz.mall.promotion.controller.app;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@RestController("appCouponController")
@RequestMapping("/app/coupon")
@RequiredArgsConstructor
public class CouponController {

	private final CouponService couponService;

	/**
	 * 根据id查询优惠券信息
	 * @param ids 优惠券id集合
	 */
	@Ojbk
	@GetMapping(value = "/list")
	public Result<List<Map<String, Object>>> couponList(@RequestParam("ids") List<Long> ids) {
		return Result.success(couponService.listMaps(Wrappers.<Coupon>lambdaQuery().in(Coupon::getId, ids)));
	}

	/**
	 * 会员领取优惠券
	 * @param couponId 优惠券id
	 */
	@GetMapping("receiveCoupon/{couponId}")
	public Result<Boolean> receiveCoupon(@PathVariable("couponId") Long couponId) {
		return Result.judge(couponService.memberReceiveCoupon(couponId));
	}

}