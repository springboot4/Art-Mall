package com.fxz.mall.promotion.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.entity.MemberCoupon;
import com.fxz.mall.promotion.entity.PromotionGoods;
import com.fxz.mall.promotion.enums.*;
import com.fxz.mall.promotion.mapper.CouponMapper;
import com.fxz.mall.promotion.service.CouponActivityItemService;
import com.fxz.mall.promotion.service.CouponService;
import com.fxz.mall.promotion.service.MemberCouponService;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import com.fxz.mall.promotion.vo.CouponVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

	private final MemberCouponService memberCouponService;

	private final PromotionGoodsService promotionGoodsService;

	private final CouponActivityItemService couponActivityItemService;

	/**
	 * 新增优惠券
	 */
	@Override
	public Boolean addCoupon(CouponVO couponVO) {
		// 初始化优惠券使用数量
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
	 * 更新优惠券
	 * @param couponVO 优惠券信息
	 */
	@Override
	public Boolean updateCoupon(CouponVO couponVO) {
		// 检验优惠券是否存在
		this.checkStatus(couponVO.getId());

		// 检验优惠券基础信息合法性
		this.checkCouponInfo(couponVO);

		// 检验优惠券促销适用范围
		this.checkCouponScope(couponVO);

		// 更新优惠券信息
		this.saveOrUpdate(couponVO);

		// 更新促销商品信息
		this.updatePromotionsGoods(couponVO);

		return Boolean.TRUE;
	}

	/**
	 * 会员领取优惠券
	 * @param couponId 优惠券id
	 */
	@Override
	public Boolean memberReceiveCoupon(Long couponId) {
		Coupon coupon = this.getById(couponId);
		if (Objects.isNull(coupon) || CouponGetEnum.FREE.getValue().equals(coupon.getGetType())) {
			throw new FxzException("优惠券信息不存在！");
		}

		FxzAuthUser user = SecurityUtil.getUser();

		// 检查优惠券领取数量
		this.checkCouponLimit(couponId, user.getUserId());

		MemberCoupon memberCoupon = new MemberCoupon(coupon);
		memberCoupon.setMemberId(user.getUserId());
		memberCoupon.setMemberName(user.getUsername());
		memberCoupon.setMemberCouponStatus(MemberCouponStatusEnum.RECEIVE.getValue());
		memberCouponService.save(memberCoupon);

		this.receiveCoupon(couponId, 1);

		return Boolean.TRUE;
	}

	/**
	 * 关闭优惠券
	 * @param id 优惠券id
	 */
	@Override
	public Boolean closeCoupon(Long id) {
		return this.updateStatus(id, null, null);
	}

	/**
	 * 更新优惠券的状态
	 * @param id 优惠券id
	 * @param startTime 活动开始时间
	 * @param endTime 活动结束时间
	 */
	@Override
	public Boolean updateStatus(Long id, LocalDateTime startTime, LocalDateTime endTime) {
		// 查询数据库中优惠券信息
		Coupon coupon = this.getById(id);
		if (Objects.isNull(coupon)) {
			return Boolean.FALSE;
		}

		// 根据优惠券id更新促销商品信息
		CouponVO couponVO = new CouponVO();
		couponVO.setId(id);
		couponVO.setStartTime(startTime);
		couponVO.setEndTime(endTime);
		this.updatePromotionsGoods(couponVO);

		// 更新优惠券信息
		return this.update(Wrappers.<Coupon>lambdaUpdate().eq(Coupon::getId, couponVO.getId())
				.set(Coupon::getStartTime, startTime).set(Coupon::getEndTime, endTime));
	}

	/**
	 * 更新促销商品信息
	 */
	@Override
	public void updatePromotionsGoods(Coupon coupon) {
		// 删除促销商品信息
		promotionGoodsService
				.remove(Wrappers.<PromotionGoods>lambdaQuery().eq(PromotionGoods::getPromotionId, coupon.getId()));

		// 如果优惠券的开始和结束时间都是null，我们认为此优惠券过期，删除掉优惠券下所有的促销商品信息
		if (Objects.isNull(coupon.getStartTime()) && Objects.isNull(coupon.getEndTime())) {
			return;
		}

		// 如果是全品类或者部分品类 保存品类id
		if (CharSequenceUtil.equalsAny(coupon.getScopeType(), PromotionsScopeTypeEnum.ALL.getValue(),
				PromotionsScopeTypeEnum.PORTION_GOODS_CATEGORY.getValue())) {
			// 构建促销商品信息
			PromotionGoods promotionGoods = new PromotionGoods();
			promotionGoods.setScopeId(coupon.getScopeId());
			promotionGoods.setScopeType(coupon.getScopeType());
			promotionGoods.setPromotionId(coupon.getId());
			promotionGoods.setStartTime(coupon.getStartTime());
			promotionGoods.setEndTime(coupon.getEndTime());
			promotionGoods.setPromotionType(PromotionTypeEnum.COUPON.getValue());
			promotionGoods.setTitle(coupon.getPromotionName());

			// 保存促销商品信息
			promotionGoodsService.save(promotionGoods);
		}

		// 如果是指定商品
		if ((coupon instanceof CouponVO) && !PromotionsStatusEnum.CLOSE.getValue().equals(coupon.getPromotionStatus())
				&& PromotionsScopeTypeEnum.PORTION_GOODS.getValue().equals(coupon.getScopeType())) {
			// 保存促销商品信息
			promotionGoodsService.saveBatch(createPromotionGoods((CouponVO) coupon));
		}
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
	 * 查看优惠券信息
	 * @param id 优惠券id
	 * @return 优惠券视图对象
	 */
	@Override
	public CouponVO couponInfo(Long id) {
		Coupon coupon = this.getById(id);
		CouponVO couponVO = new CouponVO();
		BeanUtils.copyProperties(coupon, couponVO);
		couponVO.setPromotionGoodsList(this.promotionGoodsService
				.list(Wrappers.<PromotionGoods>lambdaQuery().eq(PromotionGoods::getPromotionId, id)));
		return couponVO;
	}

	@Override
	public void checkStatus(Long id) {
		// 检验优惠券是否存在
		Coupon coupon = this.getById(id);
		if (Objects.isNull(coupon)) {
			throw new FxzException("优惠券信息信息不存在!");
		}
	}

	/**
	 * 领取优惠券
	 * @param couponId 优惠券id
	 * @param receiveNum 领取数量
	 */
	@Override
	public void receiveCoupon(Long couponId, Integer receiveNum) {
		Coupon coupon = this.getById(couponId);
		if (Objects.isNull(coupon)) {
			throw new FxzException("优惠券不存在！");
		}

		this.update(Wrappers.<Coupon>lambdaUpdate().set(Coupon::getReceivedNum, coupon.getReceivedNum() + receiveNum)
				.eq(Coupon::getId, couponId));
	}

	/**
	 * 删除优惠券
	 * @param id 优惠券id
	 * @return {@code Boolean}
	 */
	@Override
	public Boolean removeCoupon(Long id) {
		// 关闭会员优惠券
		memberCouponService.closeCoupon(id);

		// 删除优惠券关联活动优惠券
		couponActivityItemService.removeByCouponId(id);

		// 检查优惠券状态
		this.checkStatus(id);

		Coupon coupon = this.getById(id);
		// 删除促销商品
		this.updatePromotionsGoods((Coupon) coupon.setStartTime(null).setEndTime(null));

		// 删除优惠券
		return this.removeById(id);
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

	public void checkCouponLimit(Long couponId, Long memberId) {
		Coupon coupon = this.getById(couponId);
		LambdaQueryWrapper<MemberCoupon> queryWrapper = new LambdaQueryWrapper<MemberCoupon>()
				.eq(MemberCoupon::getCouponId, couponId).eq(MemberCoupon::getMemberId, memberId);
		long haveCoupons = memberCouponService.count(queryWrapper);
		if (!PromotionsStatusEnum.START.getValue().equals(coupon.getPromotionStatus())) {
			throw new FxzException("优惠券已过期！");
		}
		if (coupon.getPublishNum() != 0 && coupon.getReceivedNum() >= coupon.getPublishNum()) {
			throw new FxzException("优惠券剩余领取数量不足！");
		}
		if (!coupon.getCouponLimitNum().equals(0) && haveCoupons >= coupon.getCouponLimitNum()) {
			throw new FxzException("此优惠券最多领取" + coupon.getCouponLimitNum() + "张");
		}
	}

}