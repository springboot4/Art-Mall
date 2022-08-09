package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Data
@TableName("seckill")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Seckill extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 活动结束时间
	 */
	private LocalDateTime endTime;

	/**
	 * 活动名称
	 */
	private String promotionName;

	/**
	 * 活动开始时间
	 */
	private LocalDateTime startTime;

	/**
	 * 报名截至时间
	 */
	private LocalDateTime applyEndTime;

	/**
	 * 开启几点场
	 */
	private String hours;

	/**
	 * 申请规则
	 */
	private String seckillRule;

	/**
	 * 参与的商品数量
	 */
	private Integer goodsNum;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Boolean deleteFlag;

}
