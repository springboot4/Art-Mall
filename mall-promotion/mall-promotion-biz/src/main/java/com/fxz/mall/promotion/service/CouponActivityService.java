package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.entity.CouponActivity;

import java.util.List;

/**
 * 优惠券活动
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface CouponActivityService extends IService<CouponActivity> {

	/**
	 * 添加
	 */
	Boolean addActivity(CouponActivityDTO CouponActivityDTO);

	/**
	 * 修改
	 */
	Boolean updateActivity(CouponActivityDTO CouponActivityDTO);

	/**
	 * 分页
	 */
	IPage<CouponActivity> pageActivity(Page<CouponActivity> pageParam, CouponActivity couponActivity);

	/**
	 * 获取单条
	 */
	CouponActivity findById(Long id);

	/**
	 * 获取全部
	 */
	List<CouponActivity> findAll();

	/**
	 * 删除
	 */
	Boolean deleteActivity(Long id);

}