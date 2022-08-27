package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.dto.CouponDTO;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.mapper.CouponMapper;
import com.fxz.mall.promotion.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

	private final CouponMapper couponMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addCoupon(CouponDTO CouponDTO) {
		Coupon coupon = new Coupon();
		BeanUtils.copyProperties(CouponDTO, coupon);
		couponMapper.insert(coupon);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateCoupon(CouponDTO CouponDTO) {
		Coupon coupon = new Coupon();
		BeanUtils.copyProperties(CouponDTO, coupon);
		couponMapper.updateById(coupon);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Coupon> pageCoupon(Page<Coupon> pageParam, Coupon coupon) {
		return couponMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Coupon findById(Long id) {
		return couponMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Coupon> findAll() {
		return couponMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteCoupon(Long id) {
		couponMapper.deleteById(id);
		return Boolean.TRUE;
	}

}