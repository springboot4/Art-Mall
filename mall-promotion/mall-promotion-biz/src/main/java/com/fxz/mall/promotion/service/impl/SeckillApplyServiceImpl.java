package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.fxz.mall.promotion.entity.PromotionGoods;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillApply;
import com.fxz.mall.promotion.enums.PromotionTypeEnum;
import com.fxz.mall.promotion.mapper.SeckillApplyMapper;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import com.fxz.mall.promotion.service.SeckillApplyService;
import com.fxz.mall.promotion.service.SeckillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeckillApplyServiceImpl extends ServiceImpl<SeckillApplyMapper, SeckillApply>
		implements SeckillApplyService {

	private final RemoteSkuService remoteSkuService;

	private final SeckillService seckillService;

	private final PromotionGoodsService promotionGoodsService;

	/**
	 * 秒杀活动信息更新，更新相关的秒杀请求以及促销商品信息
	 * @param seckill 秒杀活动信息
	 * @return true or false
	 */
	@Override
	public boolean updateSeckillApplyTime(Seckill seckill) {
		// 查出当前秒杀活动下的商家请求
		List<SeckillApply> seckillApplies = this
				.list(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckill));
		// 筛选出查询当前秒杀活动修改后在时间段内的商家请求
		List<SeckillApply> applies = seckillApplies.stream()
				.filter(seckillApply -> Objects.nonNull(seckillApply.getTimeLine())
						&& seckill.getHours().contains(seckillApply.getTimeLine().toString()))
				.collect(Collectors.toList());

		// 为每个商家请求构建促销商品
		List<PromotionGoods> promotionGoodsList = applies.stream().map(applie -> {
			// 获取参与活动的sku信息
			SkuInfoDTO skuInfo = remoteSkuService.getSkuInfo(applie.getSkuId()).getData();
			// 构建促销商品
			return this.createSeckillGoods(skuInfo, applie, seckill);
		}).collect(Collectors.toList());

		if (!promotionGoodsList.isEmpty()) {
			// 删掉之前的所有促销活动
			promotionGoodsService
					.remove(Wrappers.<PromotionGoods>lambdaQuery().eq(PromotionGoods::getPromotionId, seckill.getId())
							.eq(PromotionGoods::getPromotionType, PromotionTypeEnum.SECKILL.getValue()));
			// 保存本次构建的促销活动
			promotionGoodsService.saveBatch(promotionGoodsList);
		}

		// todo 更新es中商品的促销信息

		// 删掉修改后不在时间段下的商家请求
		return this.remove(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckill.getId()).notIn(
				SeckillApply::getSkuId,
				promotionGoodsList.stream().map(PromotionGoods::getSkuId).collect(Collectors.toList())));
	}

	/**
	 * 删除秒杀活动申请
	 */
	@Override
	public void removeSeckillApply(String seckillId, String id) {
		SeckillApply apply = this.getById(id);
		if (Objects.isNull(apply)) {
			throw new FxzException("当前参与的秒杀活动不存在");
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (Objects.isNull(seckill)) {
			throw new FxzException("当前秒杀活动不存在");
		}

		// 清除秒杀活动中的商品
		this.remove(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckillId)
				.eq(SeckillApply::getId, id));

		// todo 更新es商品中的促销信息

		// 删除促销商品
		promotionGoodsService.remove(Wrappers.<PromotionGoods>lambdaQuery()
				.eq(PromotionGoods::getPromotionId, seckillId).eq(PromotionGoods::getSkuId, id));
	}

	/**
	 * 构建促销商品
	 * @param skuInfo sku信息
	 * @param applie 商家请求
	 * @param seckill 秒杀信息
	 * @return 促销商品信息
	 */
	private PromotionGoods createSeckillGoods(SkuInfoDTO skuInfo, SeckillApply applie, Seckill seckill) {
		// 促销商品默认信息
		PromotionGoods promotionGoods = new PromotionGoods(skuInfo);
		promotionGoods.setPrice(applie.getPrice());
		promotionGoods.setQuantity(applie.getQuantity());
		promotionGoods.setPromotionType(PromotionTypeEnum.SECKILL.getValue());
		promotionGoods.setTitle(seckill.getPromotionName());
		promotionGoods.setStartTime(seckill.getStartTime().withHour(applie.getTimeLine()));
		promotionGoods.setEndTime(seckill.getEndTime());
		return promotionGoods;
	}

}