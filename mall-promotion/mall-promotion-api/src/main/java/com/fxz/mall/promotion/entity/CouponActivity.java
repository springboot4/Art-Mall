package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 优惠券活动
 *
 * @author fxz
 * @date 2022-08-27
 */
@Data
@TableName("coupon_activity")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CouponActivity extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * id主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 活动名称
	 */
	private String promotionName;

	/**
	 * 活动开始时间
	 */
	private LocalDateTime startTime;

	/**
	 * 活动结束时间
	 */
	private LocalDateTime endTime;

	/**
	 * 范围关联的ID
	 */
	private String scopeId;

	/**
	 * 关联范围类型
	 */
	private String scopeType;

	/**
	 * 优惠券活动类型
	 *
	 * @see com.fxz.mall.promotion.enums.CouponActivityTypeEnum
	 */
	private String couponActivityType;

	/**
	 * 活动范围详情,只有精准发券使用
	 */
	private String activityScopeInfo;

	/**
	 * 优惠券发放范围（新人注册赠券不用选择此发放范围）
	 *
	 * @see com.fxz.mall.promotion.enums.CouponActivitySendTypeEnum
	 */
	private String activityScope;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Integer deleteFlag;

}
