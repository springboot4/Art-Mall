package com.fxz.mall.promotion.dto;

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
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CouponDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String promotionName;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String couponName;

	private String getType;

	private Double consumeThreshold;

	private Double couponDiscount;

	private Integer couponLimitNum;

	private String couponType;

	private String description;

	private Double price;

	private Integer publishNum;

	private Integer receivedNum;

	private String scopeId;

	private String scopeType;

	private Integer usedNum;

	private String rangeDayType;

	private Integer effectiveDays;

	private Integer deleteFlag;

}