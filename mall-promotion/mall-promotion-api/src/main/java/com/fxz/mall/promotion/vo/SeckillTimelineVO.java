package com.fxz.mall.promotion.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 秒杀活动时刻视图对象
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/10 16:08
 */
@Data
public class SeckillTimelineVO implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 时刻
	 */
	private Integer timeLine;

	/**
	 * 秒杀开始时间，这个是时间戳
	 */
	private Long startTime;

	/**
	 * 距离本组活动开始的时间，秒为单位。如果活动的开始时间是10点，服务器时间为8点，距离开始还有多少时间
	 */
	private Long distanceStartTime;

	/**
	 * 本组活动内的秒杀活动商品列表
	 */
	private List<SeckillGoodsVO> seckillGoodsList;

}
