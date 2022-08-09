package com.fxz.mall.promotion.entity;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import com.fxz.mall.promotion.SeckillVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Date;

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

	/**
	 * 默认创建day天后的秒杀活动
	 * @param day 几天后
	 * @param hours 几点场
	 * @param seckillRule 规则
	 */
	public Seckill(int day, String hours, String seckillRule) {
		// 一天的开始时间
		DateTime dateTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), day));
		// 报名截至时间
		this.applyEndTime = DateUtil.toLocalDateTime(dateTime);
		// 开启几点场
		this.hours = hours;
		// 规则
		this.seckillRule = seckillRule;
		// 参与商品数量
		this.goodsNum = 0;
		// 活动名称
		this.promotionName = DateUtil.formatDate(dateTime) + " 秒杀活动";
		// 活动开始时间
		this.startTime = DateUtil.toLocalDateTime(dateTime);
		// 活动结束时间
		this.endTime = DateUtil.toLocalDateTime(DateUtil.endOfDay(dateTime));
	}

	public Seckill(SeckillVO seckillVO) {
		BeanUtils.copyProperties(seckillVO, this);
	}

	public Seckill() {
	}

}
