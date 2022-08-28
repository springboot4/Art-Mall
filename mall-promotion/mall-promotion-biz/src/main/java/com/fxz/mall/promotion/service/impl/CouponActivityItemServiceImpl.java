package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.entity.CouponActivityItem;
import com.fxz.mall.promotion.mapper.CouponActivityItemMapper;
import com.fxz.mall.promotion.service.CouponActivityItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券活动项
 *
 * @author fxz
 * @date 2022-08-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponActivityItemServiceImpl extends ServiceImpl<CouponActivityItemMapper, CouponActivityItem>
		implements CouponActivityItemService {

	/**
	 * 根据优惠券活动id获取优惠券项
	 * @param id 优惠券活动id
	 */
	@Override
	public List<CouponActivityItem> getCouponActivityList(Long id) {
		return this.list(Wrappers.<CouponActivityItem>lambdaQuery().eq(CouponActivityItem::getActivityId, id));
	}

}