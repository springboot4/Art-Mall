package com.fxz.mall.promotion.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/9 16:30
 */
@Data
public class SeckillSetting implements Serializable {

	/**
	 * 开启几点场 例如：6,8,12
	 */
	@NotNull(message = "活动时间段不能为空")
	private String hours;

	/**
	 * 秒杀规则
	 */
	@NotNull(message = "秒杀规则不能为空")
	private String seckillRule;

}
