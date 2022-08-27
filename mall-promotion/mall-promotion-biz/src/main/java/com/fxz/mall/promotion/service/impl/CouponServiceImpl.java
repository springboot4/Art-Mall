package com.fxz.mall.promotion.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.entity.PromotionGoods;
import com.fxz.mall.promotion.enums.*;
import com.fxz.mall.promotion.mapper.CouponMapper;
import com.fxz.mall.promotion.service.CouponService;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import com.fxz.mall.promotion.vo.CouponVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

	private final RemoteSkuService remoteSkuService;

	private final PromotionGoodsService promotionGoodsService;

	/**
	 * 新增优惠券
	 */
	@Override
	public Boolean addCoupon(CouponVO couponVO) {
		// 初始化优惠券适用数量
		couponVO.setUsedNum(0);

		// 初始化优惠券领取数量
		couponVO.setReceivedNum(0);

		// 检验优惠券基础信息合法性
		this.checkCouponInfo(couponVO);

		// 检验优惠券促销适用范围
		this.checkCouponScope(couponVO);

		// 保存优惠券信息
		this.save(couponVO);

		// 更新促销商品信息
		this.updatePromotionsGoods(couponVO);

		return Boolean.TRUE;
	}

	/**
	 * 检验优惠券基础信息合法性
	 */
	@Override
	public void checkCouponInfo(Coupon coupon) {
		// 优惠券限制领取数量
		if (coupon.getCouponLimitNum() < 0) {
			throw new FxzException("优惠券限制领取数量不能小于0!");
		}

		// 发行数量小于限制领取的数量
		if (coupon.getPublishNum() != 0 && coupon.getCouponLimitNum() > coupon.getPublishNum()) {
			throw new FxzException("发行数量小于限制领取的数量!");
		}

		// 优惠券折扣范围不合法
		boolean discountCoupon = (coupon.getCouponType().equals(CouponTypeEnum.DISCOUNT.getValue())
				&& (coupon.getCouponDiscount() < 0 || coupon.getCouponDiscount() > 10));
		if (discountCoupon) {
			throw new FxzException("优惠券折扣范围不合法");
		}

		// 如果优惠券适用时间范围为固定时间，那么检验优惠券时间合法性
		if (Objects.nonNull(coupon.getRangeDayType())
				&& coupon.getRangeDayType().equals(CouponRangeDayEnum.FIXEDTIME.getValue())) {
			this.checkPromotionsTime(coupon.getStartTime(), coupon.getEndTime());
		}
	}

	/**
	 * 检验优惠券促销范围合法性
	 */
	@Override
	public void checkCouponScope(CouponVO coupon) {
		if (coupon.getScopeType().equals(PromotionsScopeTypeEnum.PORTION_GOODS.getValue())
				&& (CollectionUtil.isEmpty(coupon.getPromotionGoodsList()))) {
			throw new FxzException("当前关联范围类型为指定商品时，商品列表不能为空!");
		}
		else if (coupon.getScopeType().equals(PromotionsScopeTypeEnum.PORTION_GOODS.getValue())
				&& CharSequenceUtil.isEmpty(coupon.getScopeId())) {
			throw new FxzException("当前关联范围类型为指定商品时，商品列表不能为空");
		}
		else if (coupon.getScopeType().equals(PromotionsScopeTypeEnum.PORTION_GOODS_CATEGORY.getValue())
				&& CharSequenceUtil.isEmpty(coupon.getScopeId())) {
			throw new FxzException("当前关联范围类型为部分商品分类时，范围关联的id不能为空");
		}

		if (coupon.getScopeType().equals(PromotionsScopeTypeEnum.PORTION_GOODS.getValue())) {
			// 检验商品是否存在
			this.checkCouponPortionGoods(coupon);
		}
	}

	/**
	 * 检验指定商品信息
	 */
	@Override
	public void checkCouponPortionGoods(CouponVO coupon) {
		String[] split = coupon.getScopeId().split(StrPool.COMMA);
		if (split.length == 0) {
			throw new FxzException("当前关联范围类型为指定商品时，商品列表不能为空");
		}

		for (String skuId : split) {
			if (Objects.isNull(remoteSkuService.getSkuInfo(Long.valueOf(skuId)))) {
				throw new FxzException("当前商品不存在!");
			}
		}
	}

	/**
	 * 更新促销商品信息
	 */
	@Override
	public void updatePromotionsGoods(CouponVO couponVO) {
		// 删除促销商品信息
		promotionGoodsService
				.remove(Wrappers.<PromotionGoods>lambdaQuery().eq(PromotionGoods::getPromotionId, couponVO.getId()));

		// 如果是全品类或者部分品类 保存品类id
		if (CharSequenceUtil.equalsAny(couponVO.getScopeType(), PromotionsScopeTypeEnum.ALL.getValue(),
				PromotionsScopeTypeEnum.PORTION_GOODS_CATEGORY.getValue())) {
			// 构建促销商品信息
			PromotionGoods promotionGoods = new PromotionGoods();
			promotionGoods.setScopeId(couponVO.getScopeId());
			promotionGoods.setScopeType(couponVO.getScopeType());
			promotionGoods.setPromotionId(couponVO.getId());
			promotionGoods.setStartTime(couponVO.getStartTime());
			promotionGoods.setEndTime(couponVO.getEndTime());
			promotionGoods.setPromotionType(PromotionTypeEnum.COUPON.getValue());
			promotionGoods.setTitle(couponVO.getPromotionName());

			// 保存促销商品信息
			promotionGoodsService.save(promotionGoods);
		}

		// 如果是指定商品
		if (!PromotionsStatusEnum.CLOSE.getValue().equals(couponVO.getPromotionStatus())
				&& PromotionsScopeTypeEnum.PORTION_GOODS.getValue().equals(couponVO.getScopeType())) {
			List<PromotionGoods> promotionGoodsList = createPromotionGoods(couponVO);

			// 保存促销商品信息
			promotionGoodsService.saveBatch(promotionGoodsList);
		}

	}

	/**
	 * 构建促销商品
	 */
	private List<PromotionGoods> createPromotionGoods(CouponVO couponVO) {
		List<PromotionGoods> promotionGoodsList = couponVO.getPromotionGoodsList();

		promotionGoodsList.forEach(promotionGoods -> {
			promotionGoods.setPromotionId(couponVO.getId());
			promotionGoods.setTitle(couponVO.getPromotionName());
			promotionGoods.setStartTime(couponVO.getStartTime());
			promotionGoods.setEndTime(couponVO.getEndTime());
			promotionGoods.setPromotionType(PromotionTypeEnum.COUPON.getValue());
			promotionGoods.setNum(0);
			promotionGoods.setDeleteFlag(couponVO.getDeleteFlag());
		});

		return promotionGoodsList;
	}

	/**
	 * 检验优惠券促销时间合法性
	 */
	@Override
	public void checkPromotionsTime(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime == null) {
			throw new FxzException("活动开始时间为空！");
		}

		LocalDateTime now = LocalDateTime.now();

		// 活动起始时间小于现在时间
		if (now.isAfter(startTime)) {
			throw new FxzException("活动开始时间不能早于当前时间！");
		}

		// 活动结束时间小于现在时间
		if (Objects.nonNull(endTime) && now.isAfter(endTime)) {
			throw new FxzException("活动结束时间不能早于当前时间！");
		}

		// 开始时间不能大于结束时间
		if (Objects.nonNull(endTime) && startTime.isAfter(endTime)) {
			throw new FxzException("活动结束时间不能早于当前时间！");
		}
	}

}