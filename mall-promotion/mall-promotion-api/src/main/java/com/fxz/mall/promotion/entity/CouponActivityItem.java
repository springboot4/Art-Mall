package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Data
@TableName("coupon_activity_item")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CouponActivityItem extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * id主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 优惠券活动id
	 */
	private Long activityId;

	/**
	 * 优惠券id
	 */
	private Long couponId;

	/**
	 * 优惠券数量
	 */
	private Integer num;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Integer deleteFlag;

}
