package com.fxz.mall.promotion.service.impl;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.member.entity.Member;
import com.fxz.mall.member.feign.RemoteMemberService;
import com.fxz.mall.promotion.dto.CouponActivityDTO;
import com.fxz.mall.promotion.dto.MemberDTO;
import com.fxz.mall.promotion.entity.Coupon;
import com.fxz.mall.promotion.entity.CouponActivity;
import com.fxz.mall.promotion.entity.CouponActivityItem;
import com.fxz.mall.promotion.entity.MemberCoupon;
import com.fxz.mall.promotion.enums.*;
import com.fxz.mall.promotion.mapper.CouponActivityMapper;
import com.fxz.mall.promotion.service.CouponActivityItemService;
import com.fxz.mall.promotion.service.CouponActivityService;
import com.fxz.mall.promotion.service.CouponService;
import com.fxz.mall.promotion.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

	private final RemoteMemberService remoteMemberService;

	private final CouponService couponService;

	private final MemberCouponService memberCouponService;

	private final CouponActivityItemService couponActivityItemService;

	/**
	 * 添加优惠券活动
	 * @param couponActivity 优惠券活动
	 */
	@Override
	public void saveCouponActivity(CouponActivityDTO couponActivity) {
		// 初始化优惠券活动
		this.initCouponActivity(couponActivity);

		// 检验优惠券活动的合法性
		this.checkCouponActivity(couponActivity);

		// 保存优惠券活动信息
		this.save(couponActivity);

		// 更新促销商品信息
		this.updatePromotionsGoods(couponActivity);

		// 如果是精准发券，我们开始为会员发放优惠券
		if (!PromotionsStatusEnum.CLOSE.getValue().equals(couponActivity.getPromotionStatus())
				&& couponActivity.getCouponActivityType().equals(CouponActivityTypeEnum.SPECIFY.getValue())) {
			this.activitySend(couponActivity.getId());
		}
	}

	/**
	 * 保存优惠券活动项
	 * @param couponActivity 优惠券活动
	 */
	@Override
	public void updatePromotionsGoods(CouponActivityDTO couponActivity) {
		couponActivity.getCouponActivityItems().forEach(item -> item.setActivityId(couponActivity.getId()));
		couponActivityItemService.saveBatch(couponActivity.getCouponActivityItems());
	}

	/**
	 * 关闭券活动
	 * @param id 券活动id
	 */
	@Override
	public void closeCouponActivity(Long id) {
		CouponActivity activity = this.getById(id);
		if (Objects.isNull(activity)) {
			throw new FxzException("当前活动不存在!");
		}

		this.update(Wrappers.<CouponActivity>lambdaUpdate().eq(CouponActivity::getId, id)
				.set(CouponActivity::getStartTime, null).set(CouponActivity::getEndTime, null));
	}

	/**
	 * 根据活动id发放优惠券
	 * @param id 优惠券活动
	 */
	@Override
	public void activitySend(Long id) {
		// 查询活动信息
		CouponActivity activity = this.getById(id);

		// 获取会员信息
		List<Map<String, Object>> memberList = this.getMemberList(activity);

		// 会员拆成多个小组进行发送
		List<List<Map<String, Object>>> memberGroup = new ArrayList<>();

		// 循环分组
		for (int i = 0; i < (memberList.size() / 100 + (memberList.size() % 100 == 0 ? 0 : 1)); i++) {
			int endPoint = Math.min((100 + (i * 100)), memberList.size());
			memberGroup.add(memberList.subList((i * 100), endPoint));
		}

		// 获取优惠券活动的优惠券列表
		List<CouponActivityItem> couponActivityItems = couponActivityItemService
				.getCouponActivityList(activity.getId());

		// 循环为每组会员发送优惠券
		for (List<Map<String, Object>> list : memberGroup) {
			sendCoupon(list, couponActivityItems);
		}
	}

	/**
	 * 发放优惠券
	 * @param list 用户列表
	 * @param couponActivityItems 优惠券列表
	 */
	private void sendCoupon(List<Map<String, Object>> list, List<CouponActivityItem> couponActivityItems) {
		// 循环每一个优惠券项
		for (CouponActivityItem couponActivityItem : couponActivityItems) {
			// 获取优惠券
			Coupon coupon = couponService.getById(couponActivityItem.getCouponId());
			if (Objects.isNull(coupon)) {
				throw new FxzException("赠送优惠券失败,当前优惠券不存在:" + couponActivityItem.getCouponId());
			}

			List<MemberCoupon> memberCouponList = new LinkedList<>();

			// 循环优惠券的领取数量
			for (int i = 1; i <= couponActivityItem.getNum(); i++) {
				// 循环会员列表，添加优惠券
				for (Map<String, Object> map : list) {
					// 会员优惠券信息
					MemberCoupon memberCoupon = new MemberCoupon(coupon);
					// 会员id
					memberCoupon.setMemberId(Long.valueOf(map.get(Member.Fields.id).toString()));
					// 会员昵称
					memberCoupon
							.setMemberName(map.get(StringUtils.camelToUnderline(Member.Fields.nickName)).toString());
					// 会员优惠券的使用状态
					memberCoupon.setMemberCouponStatus(MemberCouponStatusEnum.RECEIVE.getValue());
					memberCouponList.add(memberCoupon);
				}
			}

			// 批量添加优惠券
			memberCouponService.saveBatch(memberCouponList);
			// 更新优惠券已领取数量
			couponService.receiveCoupon(couponActivityItem.getCouponId(), memberCouponList.size());
		}
	}

	/**
	 * 根据优惠券活动信息获取会员信息
	 * @param activity 优惠券活动信息
	 * @return 会员信息
	 */
	@Override
	public List<Map<String, Object>> getMemberList(CouponActivity activity) {
		List<Long> ids;
		if (CouponActivitySendTypeEnum.ALL.getValue().equals(activity.getActivityScope())) {
			// 如果是全员活动查询全部会员信息
			ids = null;
		}
		else {
			// 如果是指定会员获取储存的会员信息
			List<MemberDTO> list = JacksonUtil.parseArray(activity.getActivityScopeInfo(), MemberDTO.class);
			ids = list.stream().map(MemberDTO::getId).collect(Collectors.toList());
		}

		return remoteMemberService.listMemberMap(
				Member.Fields.id + StrPool.COMMA + StringUtils.camelToUnderline(Member.Fields.nickName), ids).getData();
	}

	/**
	 * 初始化优惠券活动
	 * @param couponActivity 优惠券活动
	 */
	@Override
	public void initCouponActivity(CouponActivityDTO couponActivity) {
		// 设置优惠券活动的适用范围类型为指定商品类型
		couponActivity.setScopeType(PromotionsScopeTypeEnum.PORTION_GOODS.getValue());

		if (CollectionUtils.isEmpty(couponActivity.getMembers())) {
			return;
		}

		// 如果有会员信息，以json形式存储为活动范围信息
		couponActivity.setActivityScopeInfo(JacksonUtil.toJsonString(couponActivity.getMembers()));
	}

	/**
	 * 检验优惠券活动的合法性
	 * @param couponActivity 优惠券活动
	 */
	@Override
	public void checkCouponActivity(CouponActivityDTO couponActivity) {
		// 检验优惠券活动时间的合法性
		this.checkPromotionsTime(couponActivity.getStartTime(), couponActivity.getEndTime());

		// 精确发券指定会员模式下会员信息判断
		if (couponActivity.getCouponActivityType().equals(CouponActivityTypeEnum.SPECIFY.getValue())
				&& couponActivity.getActivityScope().equals(CouponActivitySendTypeEnum.DESIGNATED.getValue())
				&& StrUtil.isBlank(couponActivity.getActivityScopeInfo())) {
			throw new FxzException("精确发券指定会员模式下会员信息为空！");
		}

		// 检验优惠券项
		this.checkCouponActivityItem(couponActivity.getCouponActivityItems());
	}

	/**
	 * 检验优惠券活动时间的合法性
	 * @param startTime 活动开始时间
	 * @param endTime 活动结束时间
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

	/**
	 * 检验优惠券项
	 * @param couponActivityItems 优惠券项
	 */
	@Override
	public void checkCouponActivityItem(List<CouponActivityItem> couponActivityItems) {
		if (CollectionUtils.isEmpty(couponActivityItems)) {
			throw new FxzException("优惠券活动下优惠券信息为空！");
		}

		couponActivityItems.forEach(item -> {
			if (Objects.isNull(item.getNum()) || item.getNum() < 0) {
				throw new FxzException("优惠券限领数量不合法！");
			}
		});
	}

}