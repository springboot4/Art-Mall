package com.fxz.mall.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.promotion.dto.SeckillDTO;
import com.fxz.mall.promotion.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Mapper
public interface SeckillMapper extends BaseMapper<Seckill> {

	/**
	 * 根据秒杀活动Id获取秒杀活动以及秒杀活动下的商家秒杀请求
	 * @param seckillId 秒杀活动id
	 */
	SeckillDTO getSeckillAndApplyById(@Param("seckillId") Long seckillId);

}