package com.fxz.mall.promotion.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 会员优惠券
 *
 * @author fxz
 * @date 2022-08-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class MemberCouponDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Double consumeThreshold;

	private LocalDateTime consumptionTime;

	private Long couponId;

	private String couponType;

	private Double discount;

	private LocalDateTime endTime;

	private String getType;

	private String memberCouponStatus;

	private Long memberId;

	private String memberName;

	private Double price;

	private String scopeId;

	private String scopeType;

	private LocalDateTime startTime;

	private Integer deleteFlag;

}