package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.dto.CouponDTO;
import com.fxz.mall.promotion.entity.Coupon;

import java.util.List;

/**
 * 优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface CouponService extends IService<Coupon> {

	/**
	 * 添加
	 */
	Boolean addCoupon(CouponDTO CouponDTO);

	/**
	 * 修改
	 */
	Boolean updateCoupon(CouponDTO CouponDTO);

	/**
	 * 分页
	 */
	IPage<Coupon> pageCoupon(Page<Coupon> pageParam, Coupon coupon);

	/**
	 * 获取单条
	 */
	Coupon findById(Long id);

	/**
	 * 获取全部
	 */
	List<Coupon> findAll();

	/**
	 * 删除
	 */
	Boolean deleteCoupon(Long id);

}