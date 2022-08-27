package com.fxz.mall.promotion.dto;

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
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CouponActivityDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String promotionName;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String scopeId;

	private String scopeType;

	private String activityScope;

	private String activityScopeInfo;

	private String couponActivityType;

	private Integer deleteFlag;

}