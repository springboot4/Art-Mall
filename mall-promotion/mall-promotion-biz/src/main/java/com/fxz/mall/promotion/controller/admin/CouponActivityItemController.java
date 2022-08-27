package com.fxz.mall.promotion.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.dto.CouponActivityItemDTO;
import com.fxz.mall.promotion.entity.CouponActivityItem;
import com.fxz.mall.promotion.service.CouponActivityItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券活动项
 *
 * @author fxz
 * @date 2022-08-27
 */
@RestController
@RequestMapping("/admin/couponActivityItem")
@RequiredArgsConstructor
public class CouponActivityItemController {

	private final CouponActivityItemService couponActivityItemService;

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody CouponActivityItemDTO CouponActivityItemDTO) {
		return Result.success(couponActivityItemService.addCouponActivityItem(CouponActivityItemDTO));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody CouponActivityItemDTO CouponActivityItemDTO) {
		return Result.success(couponActivityItemService.updateCouponActivityItem(CouponActivityItemDTO));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(couponActivityItemService.deleteCouponActivityItem(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<CouponActivityItem> findById(Long id) {
		return Result.success(couponActivityItemService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<CouponActivityItem>> findAll() {
		return Result.success(couponActivityItemService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<CouponActivityItem>> pageCouponActivityItem(Page<CouponActivityItem> pageParam,
			CouponActivityItem couponActivityItem) {
		return Result.success(
				PageResult.success(couponActivityItemService.pageCouponActivityItem(pageParam, couponActivityItem)));
	}

}