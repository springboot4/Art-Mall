package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.dto.CouponActivityItemDTO;
import com.fxz.mall.promotion.entity.CouponActivityItem;
import com.fxz.mall.promotion.mapper.CouponActivityItemMapper;
import com.fxz.mall.promotion.service.CouponActivityItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

	private final CouponActivityItemMapper couponActivityItemMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addCouponActivityItem(CouponActivityItemDTO CouponActivityItemDTO) {
		CouponActivityItem couponActivityItem = new CouponActivityItem();
		BeanUtils.copyProperties(CouponActivityItemDTO, couponActivityItem);
		couponActivityItemMapper.insert(couponActivityItem);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateCouponActivityItem(CouponActivityItemDTO CouponActivityItemDTO) {
		CouponActivityItem couponActivityItem = new CouponActivityItem();
		BeanUtils.copyProperties(CouponActivityItemDTO, couponActivityItem);
		couponActivityItemMapper.updateById(couponActivityItem);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<CouponActivityItem> pageCouponActivityItem(Page<CouponActivityItem> pageParam,
			CouponActivityItem couponActivityItem) {
		return couponActivityItemMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public CouponActivityItem findById(Long id) {
		return couponActivityItemMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<CouponActivityItem> findAll() {
		return couponActivityItemMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteCouponActivityItem(Long id) {
		couponActivityItemMapper.deleteById(id);
		return Boolean.TRUE;
	}

}