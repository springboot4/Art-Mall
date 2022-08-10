package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.SeckillVO;
import com.fxz.mall.promotion.entity.Seckill;

/**
 * @author fxz
 * @date 2022-08-09
 */
public interface SeckillService extends IService<Seckill> {

	/**
	 * 初始化秒杀活动
	 */
	void initSeckill();

	/**
	 * 保存秒杀活动
	 */
	boolean saveSeckill(Seckill seckill);

	/**
	 * 更新秒杀活动
	 * @param seckill 秒杀活动视图对象
	 */
	boolean updateSeckill(Seckill seckill);

	/**
	 * 关闭秒杀活动
	 *
	 * @param id 秒杀活动id
	 */
	void closeSeckill(Long id);
}