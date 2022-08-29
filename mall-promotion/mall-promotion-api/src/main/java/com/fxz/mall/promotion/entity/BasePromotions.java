package com.fxz.mall.promotion.entity;

import com.fxz.common.mp.base.BaseEntity;
import com.fxz.mall.promotion.enums.PromotionsStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 促销活动顶级父类
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 17:55
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BasePromotions extends BaseEntity {

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
	 * 促销活动适用范围类型
	 *
	 * @see com.fxz.mall.promotion.enums.PromotionsScopeTypeEnum
	 */
	private String scopeType;

	/**
	 * 范围关联的ID
	 */
	private String scopeId;

	public String getPromotionStatus() {
		// 根据结束时间和开始时间判断是否结束
		if (endTime == null) {
			return startTime != null ? PromotionsStatusEnum.START.getValue() : PromotionsStatusEnum.CLOSE.getValue();
		}

		LocalDateTime now = LocalDateTime.now();

		if (now.isBefore(startTime)) {
			// 如果当前时间早于开始时间 那么是新建状态
			return PromotionsStatusEnum.NEW.getValue();
		}
		else if (endTime.isBefore(now)) {
			// 如果结束时间早于当前时间 那么是结束状态
			return PromotionsStatusEnum.END.getValue();
		}
		else if (now.isBefore(endTime)) {
			// 如果当前时间早于结束时间 那么是开始状态
			return PromotionsStatusEnum.START.getValue();
		}

		return PromotionsStatusEnum.CLOSE.getValue();
	}

}
