package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillApply;

/**
 * @author fxz
 * @date 2022-08-09
 */
public interface SeckillApplyService extends IService<SeckillApply> {

	/**
	 * 秒杀活动信息更新，更新相关的秒杀请求以及促销商品信息
	 * @param seckill 秒杀活动信息
	 * @return true or false
	 */
	boolean updateSeckillApplyTime(Seckill seckill);

}