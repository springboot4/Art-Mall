package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.entity.PromotionGoods;
import com.fxz.mall.promotion.mapper.PromotionGoodsMapper;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionGoodsServiceImpl extends ServiceImpl<PromotionGoodsMapper, PromotionGoods>
		implements PromotionGoodsService {

	/**
	 * 查询商品同一时间段参与了秒杀活动数量
	 * @param type 活动类型
	 * @param skuId 商品id
	 * @param startTime 秒杀活动开始时间
	 * @param endTime 秒杀活动结束时间
	 * @param promotionId 活动id
	 * @return 商品同一时间段参与了秒杀活动数量
	 */
	@Override
	public Integer findInnerOverlapPromotionGoods(String type, Long skuId, LocalDateTime startTime,
			LocalDateTime endTime, Long promotionId) {
		return this.baseMapper.findInnerOverlapPromotionGoods(type, skuId, startTime, endTime, promotionId);
	}

}