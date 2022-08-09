package com.fxz.mall.promotion;

import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillApply;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 秒杀活动视图对象
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/9 20:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SeckillVO extends Seckill {

	/**
	 * 当前秒杀活动下所有的秒杀申请信息
	 */
	private List<SeckillApply> seckillApplyList;

}
