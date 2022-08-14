package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.dto.SeckillDTO;
import com.fxz.mall.promotion.entity.Seckill;

import java.util.List;

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
	boolean saveSeckill(List<Seckill> seckills);

	/**
	 * 更新秒杀活动
	 * @param seckill 秒杀活动视图对象
	 */
	boolean updateSeckill(Seckill seckill);

	/**
	 * 关闭秒杀活动
	 * @param id 秒杀活动id
	 */
	void closeSeckill(Long id);

	/**
	 * 更新秒杀活动商品数量
	 * @param seckillId 秒杀活动id
	 * @return true or false
	 */
	boolean countSeckillGoodsNum(Long seckillId);

	/**
	 * 分页查询秒杀活动
	 */
	IPage<Seckill> pageSeckill(Page pageParam);

	/**
	 * 根据秒杀活动Id获取秒杀活动以及秒杀活动下的商家秒杀请求
	 * @param seckillId 秒杀活动id
	 */
	SeckillDTO getSeckillAndApplyById(Long seckillId);

}