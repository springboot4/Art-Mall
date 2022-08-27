package com.fxz.mall.promotion.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.dto.CouponDTO;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody CouponDTO CouponDTO) {
		return Result.success(couponService.addCoupon(CouponDTO));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody CouponDTO CouponDTO) {
		return Result.success(couponService.updateCoupon(CouponDTO));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(couponService.deleteCoupon(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<Coupon> findById(Long id) {
		return Result.success(couponService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<Coupon>> findAll() {
		return Result.success(couponService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Coupon>> pageCoupon(Page<Coupon> pageParam, Coupon coupon) {
		return Result.success(PageResult.success(couponService.pageCoupon(pageParam, coupon)));
	}

}