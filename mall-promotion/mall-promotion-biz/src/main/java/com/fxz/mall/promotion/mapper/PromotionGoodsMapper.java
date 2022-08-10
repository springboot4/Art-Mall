package com.fxz.mall.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.promotion.entity.PromotionGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Mapper
public interface PromotionGoodsMapper extends BaseMapper<PromotionGoods> {

	/**
	 * 查询商品同一时间段参与了秒杀活动数量
	 * @param type 活动类型
	 * @param skuId 商品id
	 * @param startTime 秒杀活动开始时间
	 * @param endTime 秒杀活动结束时间
	 * @param promotionId 活动id
	 * @return 商品同一时间段参与了秒杀活动数量
	 */
	Integer findInnerOverlapPromotionGoods(@Param("type") String type, @Param("skuId") Long skuId,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
			@Param("promotionId") Long promotionId);

}