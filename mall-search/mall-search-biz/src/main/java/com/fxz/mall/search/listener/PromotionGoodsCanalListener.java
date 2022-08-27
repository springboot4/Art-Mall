package com.fxz.mall.search.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.product.dto.SkuDTO;
import com.fxz.mall.promotion.enums.PromotionTypeEnum;
import com.fxz.mall.search.dto.EsGoodsDTO;
import com.fxz.mall.search.entity.EsPromotionGoods;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * 监听数据库商品信息的变化，同步商品信息到es
 *
 * @author fxz
 */
@SuppressWarnings("all")
@Slf4j
@Component
@RequiredArgsConstructor
public class PromotionGoodsCanalListener extends BaseCanalBinlogEventProcessor<EsPromotionGoods> {

	private final ElasticsearchClient elasticsearchClient;

	/**
	 * 新增促销商品信息
	 */
	@SneakyThrows
	@Override
	protected void processInsertInternal(CanalBinLogResult<EsPromotionGoods> result) {
		log.info("新增促销商品信息:{}", result);

		// 促销商品信息
		EsPromotionGoods afterData = result.getAfterData();
		// spuId
		Long goodsId = afterData.getGoodsId();
		// skuId
		Long skuId = afterData.getSkuId();

		// es中商品索引信息
		EsGoodsDTO esGoodsDTO = elasticsearchClient
				.get(g -> g.index("product").id(goodsId.toString()), EsGoodsDTO.class).source();
		if (Objects.isNull(esGoodsDTO)) {
			return;
		}

		// 获取es中当前索引中sku的信息
		List<SkuDTO> skuList = esGoodsDTO.getSkuList();
		// 获取到要更新的sku的信息
		Optional<SkuDTO> optional = skuList.stream().filter(sku -> sku.getId().equals(skuId)).findFirst();

		if (optional.isPresent()) {
			// 要更新的sku中促销活动的信息
			Map<String, EsPromotionGoods> promotionMapJson = JacksonUtil.parseObject(
					optional.get().getPromotionMapJson() != null ? optional.get().getPromotionMapJson() : "{}",
					new TypeReference<Map<String, EsPromotionGoods>>() {
					});
			if (promotionMapJson == null || promotionMapJson.size() == 0) {
				promotionMapJson = new HashMap<>(2);
			}

			// 促销活动添加秒杀信息
			promotionMapJson.put(PromotionTypeEnum.SECKILL.getValue(), afterData);
			optional.get().setPromotionMapJson(JacksonUtil.toJsonString(promotionMapJson));
			EsGoodsDTO DTO = new EsGoodsDTO();
			DTO.setId(goodsId);
			DTO.setSkuList(skuList);
			elasticsearchClient.update(u -> u.index("product").id(DTO.getId().toString()).doc(DTO), EsGoodsDTO.class);
		}
	}

	/**
	 * 更新促销商品信息
	 */
	@SneakyThrows
	@Override
	protected void processUpdateInternal(CanalBinLogResult<EsPromotionGoods> result) {
		log.info("更新促销商品信息:{}", result);

		EsPromotionGoods afterData = result.getAfterData();

		// 删除了促销活动商品信息
		if (Objects.equals(1, afterData.getDeleteFlag())) {
			removeEsPromtion(afterData);
		}
	}

	/**
	 * 删除促销商品信息
	 */
	@SneakyThrows
	@Override
	protected void processDeleteInternal(CanalBinLogResult<EsPromotionGoods> result) {
		log.info("删除促销商品信息:{}", result);

		EsPromotionGoods afterData = result.getAfterData();
		removeEsPromtion(afterData);
	}

	private void removeEsPromtion(EsPromotionGoods afterData) throws IOException {
		// 活动id
		Long promotionId = afterData.getPromotionId();
		// spuId
		Long goodsId = afterData.getGoodsId();
		// skuId
		Long skuId = afterData.getSkuId();

		// 查询出当前索引的信息
		EsGoodsDTO esGoodsDTO = elasticsearchClient
				.get(g -> g.index("product").id(goodsId.toString()), EsGoodsDTO.class).source();
		if (Objects.isNull(esGoodsDTO)) {
			return;
		}

		// 获取es中当前索引中sku的信息
		List<SkuDTO> skuList = esGoodsDTO.getSkuList();
		// 获取到要更新的sku的信息
		Optional<SkuDTO> optional = skuList.stream().filter(sku -> sku.getId().equals(skuId)).findFirst();

		if (optional.isPresent()) {
			// 要更新的sku中促销活动的信息
			Map<String, EsPromotionGoods> promotionMapJson = JacksonUtil.parseObject(
					optional.get().getPromotionMapJson(), new TypeReference<Map<String, EsPromotionGoods>>() {
					});
			if (promotionMapJson == null || promotionMapJson.size() == 0
					|| promotionMapJson.get(PromotionTypeEnum.SECKILL.getValue()) == null) {
				return;
			}

			promotionMapJson.remove(PromotionTypeEnum.SECKILL.getValue());
			optional.get().setPromotionMapJson(JacksonUtil.toJsonString(promotionMapJson));
			EsGoodsDTO DTO = new EsGoodsDTO();
			DTO.setId(goodsId);
			DTO.setSkuList(skuList);
			elasticsearchClient.update(u -> u.index("product").id(DTO.getId().toString()).doc(DTO), EsGoodsDTO.class);
		}
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			throw new FxzException("异常", throwable);
		};
	}

}
