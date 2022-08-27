package com.fxz.mall.promotion.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.dto.MemberCouponDTO;
import com.fxz.mall.promotion.entity.MemberCoupon;
import com.fxz.mall.promotion.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody MemberCouponDTO MemberCouponDTO) {
		return Result.success(memberCouponService.addMemberCoupon(MemberCouponDTO));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody MemberCouponDTO MemberCouponDTO) {
		return Result.success(memberCouponService.updateMemberCoupon(MemberCouponDTO));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(memberCouponService.deleteMemberCoupon(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<MemberCoupon> findById(Long id) {
		return Result.success(memberCouponService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<MemberCoupon>> findAll() {
		return Result.success(memberCouponService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<MemberCoupon>> pageMemberCoupon(Page<MemberCoupon> pageParam, MemberCoupon memberCoupon) {
		return Result.success(PageResult.success(memberCouponService.pageMemberCoupon(pageParam, memberCoupon)));
	}

}