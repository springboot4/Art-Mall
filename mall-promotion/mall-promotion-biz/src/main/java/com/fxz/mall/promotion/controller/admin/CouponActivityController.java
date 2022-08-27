package com.fxz.mall.promotion.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.entity.CouponActivity;
import com.fxz.mall.promotion.service.CouponActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody CouponActivityDTO CouponActivityDTO) {
		return Result.success(couponActivityService.addActivity(CouponActivityDTO));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody CouponActivityDTO CouponActivityDTO) {
		return Result.success(couponActivityService.updateActivity(CouponActivityDTO));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(couponActivityService.deleteActivity(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<CouponActivity> findById(Long id) {
		return Result.success(couponActivityService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<CouponActivity>> findAll() {
		return Result.success(couponActivityService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<CouponActivity>> pageActivity(Page<CouponActivity> pageParam,
			CouponActivity couponActivity) {
		return Result.success(PageResult.success(couponActivityService.pageActivity(pageParam, couponActivity)));
	}

}