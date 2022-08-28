package com.fxz.mall.promotion.dto;

import com.fxz.mall.promotion.entity.CouponActivity;
import com.fxz.mall.promotion.entity.CouponActivityItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 优惠券活动
 *
 * @author fxz
 * @date 2022-08-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CouponActivityDTO extends CouponActivity {

	private static final long serialVersionUID = -1L;

	/**
	 * 优惠券列表
	 */
	private List<CouponActivityItem> couponActivityItems;

	/**
	 * 会员列表
	 */
	private List<MemberDTO> members;

}