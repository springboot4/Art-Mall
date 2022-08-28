package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import com.fxz.mall.promotion.enums.CouponRangeDayEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@Data
@NoArgsConstructor
@TableName("member_coupon")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MemberCoupon extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 消费门槛
	 */
	private Double consumeThreshold;

	/**
	 * 核销时间
	 */
	private LocalDateTime consumptionTime;

	/**
	 * 优惠券ID
	 */
	private Long couponId;

	/**
	 * 活动类型
	 */
	private String couponType;

	/**
	 * 折扣
	 */
	private Double discount;

	/**
	 * 使用截止时间
	 */
	private LocalDateTime endTime;

	/**
	 * 优惠券类型
	 */
	private String getType;

	/**
	 * 会员优惠券状态
	 */
	private String memberCouponStatus;

	/**
	 * 会员ID
	 */
	private Long memberId;

	/**
	 * 会员名称
	 */
	private String memberName;

	/**
	 * 面额
	 */
	private Double price;

	/**
	 * 范围关联的ID
	 */
	private String scopeId;

	/**
	 * 关联范围类型
	 */
	private String scopeType;

	/**
	 * 使用起始时间
	 */
	private LocalDateTime startTime;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Integer deleteFlag;

	public MemberCoupon(Coupon coupon) {
		setCouponId(coupon.getId());
		setPrice(coupon.getPrice());
		setDiscount(coupon.getCouponDiscount());
		setConsumeThreshold(coupon.getConsumeThreshold());
		setScopeType(coupon.getScopeType());
		setScopeId(coupon.getScopeId());
		setCouponType(coupon.getCouponType());
		setGetType(coupon.getGetType());

		setStartTime(Objects.isNull(coupon.getStartTime()) ? LocalDateTime.now() : coupon.getStartTime());
		if (coupon.getRangeDayType().equals(CouponRangeDayEnum.FIXEDTIME.getValue())) {
			setEndTime(coupon.getEndTime());
		}
		else {
			setEndTime(getStartTime().plusDays(coupon.getEffectiveDays()));
		}
	}

}
