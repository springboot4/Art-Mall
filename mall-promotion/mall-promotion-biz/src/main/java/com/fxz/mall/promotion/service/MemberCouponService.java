package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.dto.MemberCouponDTO;
import com.fxz.mall.promotion.entity.MemberCoupon;

import java.util.List;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
public interface MemberCouponService extends IService<MemberCoupon> {

	/**
	 * 添加
	 */
	Boolean addMemberCoupon(MemberCouponDTO MemberCouponDTO);

	/**
	 * 修改
	 */
	Boolean updateMemberCoupon(MemberCouponDTO MemberCouponDTO);

	/**
	 * 分页
	 */
	IPage<MemberCoupon> pageMemberCoupon(Page<MemberCoupon> pageParam, MemberCoupon memberCoupon);

	/**
	 * 获取单条
	 */
	MemberCoupon findById(Long id);

	/**
	 * 获取全部
	 */
	List<MemberCoupon> findAll();

	/**
	 * 删除
	 */
	Boolean deleteMemberCoupon(Long id);

}