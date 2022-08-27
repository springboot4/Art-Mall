package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@Data
@TableName("coupon")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Coupon extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
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
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * 优惠券类型
	 */
	private String getType;

	/**
	 * 消费门槛
	 */
	private Double consumeThreshold;

	/**
	 * 折扣
	 */
	private Double couponDiscount;

	/**
	 * 领取限制
	 */
	private Integer couponLimitNum;

	/**
	 * 活动类型
	 */
	private String couponType;

	/**
	 * 活动描述
	 */
	private String description;

	/**
	 * 面额
	 */
	private Double price;

	/**
	 * 发行数量
	 */
	private Integer publishNum;

	/**
	 * 已被领取的数量
	 */
	private Integer receivedNum;

	/**
	 * 范围关联的ID
	 */
	private String scopeId;

	/**
	 * 关联范围类型
	 */
	private String scopeType;

	/**
	 * 已被使用的数量
	 */
	private Integer usedNum;

	/**
	 * 时间范围类型(固定时间、动态时间)
	 */
	private String rangeDayType;

	/**
	 * 有效期
	 */
	private Integer effectiveDays;

	/**
	 * 删除标志
	 */
	private Integer deleteFlag;

}
