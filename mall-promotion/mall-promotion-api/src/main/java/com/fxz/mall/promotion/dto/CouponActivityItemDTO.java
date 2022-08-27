package com.fxz.mall.promotion.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 优惠券活动项
 *
 * @author fxz
 * @date 2022-08-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CouponActivityItemDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long activityId;

	private Long couponId;

	private Integer num;

	private Integer deleteFlag;

}