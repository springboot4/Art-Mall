package com.fxz.mall.promotion.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.entity.CouponActivity;
import com.fxz.mall.promotion.service.CouponActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

	/**
	 * 关闭券活动
	 */
	@Ojbk
	@PutMapping("/close/{id}")
	public Result<Void> closeCouponActivity(@PathVariable("id") Long id) {
		couponActivityService.closeCouponActivity(id);
		return Result.success();
	}

	/**
	 * 分页查询优惠券活动信息
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<CouponActivity>> pageCoupon(Page<CouponActivity> pageParam) {
		return Result.success(PageResult.success(couponActivityService.page(pageParam, Wrappers.emptyWrapper())));
	}

	/**
	 * 根据id查询优惠券活动
	 * @param id 优惠券活动id
	 */
	@GetMapping("/findById")
	public Result<CouponActivity> findById(@RequestParam("id") Long id) {
		return Result.success(couponActivityService.getById(id));
	}

}