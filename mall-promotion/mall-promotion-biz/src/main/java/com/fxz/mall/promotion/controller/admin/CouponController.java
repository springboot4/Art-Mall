package com.fxz.mall.promotion.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.entity.Coupon;
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
	 * 更新优惠券
	 * @param couponVO 优惠券信息
	 */
	@Ojbk
	@PutMapping(value = "/update")
	public Result<Boolean> updateCoupon(@RequestBody CouponVO couponVO) {
		return Result.judge(couponService.updateCoupon(couponVO));
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

	/**
	 * 删除优惠券
	 * @param id 优惠券id
	 * @return {@code Result<Boolean>}
	 */
	@Ojbk
	@DeleteMapping("/remove")
	public Result<Boolean> removeCoupon(@RequestParam Long id) {
		return Result.judge(couponService.removeCoupon(id));
	}

	/**
	 * 查看优惠券信息
	 * @param id 优惠券id
	 */
	@Ojbk
	@GetMapping(value = "/{id}/info")
	public Result<CouponVO> couponInfo(@PathVariable("id") Long id) {
		return Result.success(couponService.couponInfo(id));
	}

	/**
	 * 会员领取优惠券
	 * @param couponId 优惠券id
	 */
	@GetMapping("receiveCoupon/{couponId}")
	public Result<Boolean> receiveCoupon(@PathVariable("couponId") Long couponId) {
		return Result.judge(couponService.memberReceiveCoupon(couponId));
	}

	/**
	 * 分页查询优惠券信息
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Coupon>> pageCoupon(Page<Coupon> pageParam, Coupon coupon) {
		return Result.success(PageResult.success(couponService.page(pageParam, Wrappers.<Coupon>lambdaQuery()
				.like(StrUtil.isNotBlank(coupon.getCouponName()), Coupon::getCouponName, coupon.getCouponName()))));
	}

}