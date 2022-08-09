package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.entity.PromotionGoods;
import com.fxz.mall.promotion.mapper.PromotionGoodsMapper;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionGoodsServiceImpl extends ServiceImpl<PromotionGoodsMapper, PromotionGoods>
		implements PromotionGoodsService {

}