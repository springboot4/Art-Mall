package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.dto.MemberCouponDTO;
import com.fxz.mall.promotion.entity.MemberCoupon;
import com.fxz.mall.promotion.mapper.MemberCouponMapper;
import com.fxz.mall.promotion.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCouponServiceImpl extends ServiceImpl<MemberCouponMapper, MemberCoupon>
		implements MemberCouponService {

	private final MemberCouponMapper memberCouponMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addMemberCoupon(MemberCouponDTO MemberCouponDTO) {
		MemberCoupon memberCoupon = new MemberCoupon();
		BeanUtils.copyProperties(MemberCouponDTO, memberCoupon);
		memberCouponMapper.insert(memberCoupon);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateMemberCoupon(MemberCouponDTO MemberCouponDTO) {
		MemberCoupon memberCoupon = new MemberCoupon();
		BeanUtils.copyProperties(MemberCouponDTO, memberCoupon);
		memberCouponMapper.updateById(memberCoupon);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<MemberCoupon> pageMemberCoupon(Page<MemberCoupon> pageParam, MemberCoupon memberCoupon) {
		return memberCouponMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public MemberCoupon findById(Long id) {
		return memberCouponMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<MemberCoupon> findAll() {
		return memberCouponMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteMemberCoupon(Long id) {
		memberCouponMapper.deleteById(id);
		return Boolean.TRUE;
	}

}