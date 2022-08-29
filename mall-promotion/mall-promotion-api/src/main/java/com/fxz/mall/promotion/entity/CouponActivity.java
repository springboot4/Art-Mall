package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class CouponActivity extends BasePromotions {

	private static final long serialVersionUID = -1L;

	/**
	 * id主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 优惠券活动类型
	 *
	 * @see com.fxz.mall.promotion.enums.CouponActivityTypeEnum
	 */
	private String couponActivityType;

	/**
	 * 优惠券发放范围（只有精准发券使用,新人注册赠券不用选择此发放范围）
	 *
	 * @see com.fxz.mall.promotion.enums.CouponActivitySendTypeEnum
	 */
	private String activityScope;

	/**
	 * 活动范围详情,只有精准发券使用
	 */
	private String activityScopeInfo;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Integer deleteFlag;

}
