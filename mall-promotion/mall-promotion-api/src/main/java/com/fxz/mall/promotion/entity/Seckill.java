package com.fxz.mall.promotion.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.mall.promotion.VO.SeckillVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Data
@TableName("seckill")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Seckill extends BasePromotions {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

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
	private Integer deleteFlag;

	/**
	 * 默认创建day天后的秒杀活动
	 * @param day 几天后
	 * @param hours 几点场
	 * @param seckillRule 规则
	 */
	public Seckill(int day, String hours, String seckillRule) {
		// 获取当前时间
		LocalDateTime now = LocalDateTime.now();
		// 获取day天后的时间
		LocalDateTime localDateTime = now.plusDays(day);

		// 报名截至时间
		this.applyEndTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
		// 开启几点场
		this.hours = hours;
		// 秒杀规则
		this.seckillRule = seckillRule;
		// 参与商品数量
		this.goodsNum = 0;
		// 活动开始时间
		this.setStartTime(LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(0, 0, 0)));
		// 活动结束时间
		this.setEndTime(LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(23, 59, 59)));
		// 活动名称
		this.setPromotionName(localDateTime.format(DatePattern.NORM_DATE_FORMATTER) + " 秒杀活动");
	}

	public Seckill(SeckillVO seckillVO) {
		BeanUtils.copyProperties(seckillVO, this);
	}

	public Seckill() {
	}

}
