package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.entity.MemberCoupon;
import com.fxz.mall.promotion.enums.MemberCouponStatusEnum;
import com.fxz.mall.promotion.mapper.MemberCouponMapper;
import com.fxz.mall.promotion.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

	/**
	 * 关闭会员优惠券
	 * @param id 优惠券id
	 */
	@Override
	public void closeCoupon(Long id) {
		this.update(Wrappers.<MemberCoupon>lambdaUpdate().eq(MemberCoupon::getCouponId, id)
				.set(MemberCoupon::getMemberCouponStatus, MemberCouponStatusEnum.CLOSED.getValue()));
	}

}