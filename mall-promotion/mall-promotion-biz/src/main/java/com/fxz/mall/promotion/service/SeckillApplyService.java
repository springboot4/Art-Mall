package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillApply;
import com.fxz.mall.promotion.vo.SeckillApplyVO;
import com.fxz.mall.promotion.vo.SeckillTimelineVO;

import java.util.List;

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

	/**
	 * 删除秒杀活动申请
	 */
	void removeSeckillApply(Long seckillId, Long id);

	/**
	 * 添加秒杀活动申请
	 * @param seckillId 秒杀活动id
	 * @param applyVos 商家秒杀请求视图对象
	 */
	void addSeckillApply(Long seckillId, List<SeckillApplyVO> applyVos);

	/**
	 * 获取当天秒杀活动信息(时刻及对应时刻下的商品)
	 */
	List<SeckillTimelineVO> listSeckillTime();

}