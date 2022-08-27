package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.dto.CouponActivityItemDTO;
import com.fxz.mall.promotion.entity.CouponActivityItem;

import java.util.List;

/**
 * 优惠券活动项
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface CouponActivityItemService extends IService<CouponActivityItem> {

	/**
	 * 添加
	 */
	Boolean addCouponActivityItem(CouponActivityItemDTO CouponActivityItemDTO);

	/**
	 * 修改
	 */
	Boolean updateCouponActivityItem(CouponActivityItemDTO CouponActivityItemDTO);

	/**
	 * 分页
	 */
	IPage<CouponActivityItem> pageCouponActivityItem(Page<CouponActivityItem> pageParam,
			CouponActivityItem couponActivityItem);

	/**
	 * 获取单条
	 */
	CouponActivityItem findById(Long id);

	/**
	 * 获取全部
	 */
	List<CouponActivityItem> findAll();

	/**
	 * 删除
	 */
	Boolean deleteCouponActivityItem(Long id);

}