package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.PromotionGoods;

import java.time.LocalDateTime;

/**
 * @author fxz
 * @date 2022-08-09
 */
public interface PromotionGoodsService extends IService<PromotionGoods> {

	/**
	 * 查询商品同一时间段参与了秒杀活动数量
	 * @param type 活动类型
	 * @param skuId 商品id
	 * @param startTime 秒杀活动开始时间
	 * @param endTime 秒杀活动结束时间
	 * @param promotionId 活动id
	 * @return 商品同一时间段参与了秒杀活动数量
	 */
	Integer findInnerOverlapPromotionGoods(String type, Long skuId, LocalDateTime startTime, LocalDateTime endTime,
			Long promotionId);

	/**
	 * 删除数据库中过期促销商品信息
	 */
	void cleanInvalidPromotion();

}