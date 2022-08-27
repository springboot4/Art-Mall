package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.entity.CouponActivity;
import com.fxz.mall.promotion.mapper.CouponActivityMapper;
import com.fxz.mall.promotion.service.CouponActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券活动
 *
 * @author fxz
 * @date 2022-08-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponActivityServiceImpl extends ServiceImpl<CouponActivityMapper, CouponActivity>
		implements CouponActivityService {

	private final CouponActivityMapper couponActivityMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addActivity(CouponActivityDTO CouponActivityDTO) {
		CouponActivity couponActivity = new CouponActivity();
		BeanUtils.copyProperties(CouponActivityDTO, couponActivity);
		couponActivityMapper.insert(couponActivity);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateActivity(CouponActivityDTO CouponActivityDTO) {
		CouponActivity couponActivity = new CouponActivity();
		BeanUtils.copyProperties(CouponActivityDTO, couponActivity);
		couponActivityMapper.updateById(couponActivity);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<CouponActivity> pageActivity(Page<CouponActivity> pageParam, CouponActivity couponActivity) {
		return couponActivityMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public CouponActivity findById(Long id) {
		return couponActivityMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<CouponActivity> findAll() {
		return couponActivityMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteActivity(Long id) {
		couponActivityMapper.deleteById(id);
		return Boolean.TRUE;
	}

}