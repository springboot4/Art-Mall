package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.mall.promotion.enums.CouponRangeDayEnum;
import com.fxz.mall.promotion.enums.PromotionsStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Coupon extends BasePromotions {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 活动描述
	 */
	private String description;

	/**
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * 优惠券减免类型
	 *
	 * @see com.fxz.mall.promotion.enums.CouponTypeEnum
	 */
	private String couponType;

	/**
	 * 优惠券减免类型为:折扣时不为null
	 */
	private Double couponDiscount;

	/**
	 * 优惠券减免类型为:减免现金时不为null
	 */
	private Double price;

	/**
	 * 优惠券的获取方式
	 *
	 * @see com.fxz.mall.promotion.enums.CouponGetEnum
	 */
	private String getType;

	/**
	 * 消费门槛
	 */
	private Double consumeThreshold;

	/**
	 * 每个用户领取限制数量
	 */
	private Integer couponLimitNum;

	/**
	 * 优惠券发行数量
	 */
	private Integer publishNum;

	/**
	 * 优惠券已被领取的数量
	 */
	private Integer receivedNum;

	/**
	 * 优惠券已被使用的数量
	 */
	private Integer usedNum;

	/**
	 * 优惠券使用时间类型(固定时间、动态时间)
	 *
	 * @see com.fxz.mall.promotion.enums.CouponRangeDayEnum
	 */
	private String rangeDayType;

	/**
	 * 有效期
	 */
	private Integer effectiveDays;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Integer deleteFlag;

	@Override
	public String getPromotionStatus() {
		// 如果是优惠券动态时间类型并且优惠券有效期没有过，那么说明这是用户新注册是获得的券，尚未使用
		boolean register = this.rangeDayType != null && this.rangeDayType.equals(CouponRangeDayEnum.DYNAMICTIME.getValue())
				&& (this.effectiveDays != null && this.effectiveDays > 0 && this.effectiveDays <= 365);
		if (register) {
			return PromotionsStatusEnum.START.getValue();
		}

		return super.getPromotionStatus();
	}

}
