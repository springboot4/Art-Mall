package com.fxz.mall.search.listener;

import cn.hutool.core.text.StrPool;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.exception.FxzException;
import com.fxz.mall.promotion.enums.PromotionsScopeTypeEnum;
import com.fxz.mall.search.entity.EsPromotionGoods;
import com.fxz.mall.search.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

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

	private final GoodsService goodsService;

	/**
	 * 新增促销商品信息
	 */
	@SneakyThrows
	@Override
	protected void processInsertInternal(CanalBinLogResult<EsPromotionGoods> result) {
		log.info("新增促销商品信息:{}", result.getAfterData());
		// 促销商品信息
		EsPromotionGoods afterData = result.getAfterData();
		// es促销活动key为促销类型:促销活动id
		String esPromotionKey = afterData.getPromotionType() + StrPool.DASHED + afterData.getPromotionId();

		if (PromotionsScopeTypeEnum.ALL.getValue().equals(afterData.getScopeType())) {
			log.info("促销商品活动类型为全品类");
			// 如果促销活动是全品类，那么我们更新所有sku
			goodsService.updateEsGoodsIndexAll(afterData, esPromotionKey);
		}
		else if (PromotionsScopeTypeEnum.PORTION_GOODS_CATEGORY.getValue().equals(afterData.getScopeType())) {
			log.info("促销商品活动类型为部分商品分类");
			// todo 如果促销活动是部分商品分类，那么促销商品信息里面肯定有分类id 我们更新分类下的每个sku
		}
		else if (PromotionsScopeTypeEnum.PORTION_GOODS.getValue().equals(afterData.getScopeType())) {
			log.info("促销商品活动类型为指定商品");
			// 如果促销活动是指定商品类型,那么促销商品信息里面肯定有skuId 我们更新每个sku
			goodsService.updateEsGoodsIndexPromotions(afterData, esPromotionKey, afterData.getGoodsId(),
					Collections.singletonList(afterData.getSkuId()));
		}
	}

	/**
	 * 更新促销商品信息
	 */
	@SneakyThrows
	@Override
	protected void processUpdateInternal(CanalBinLogResult<EsPromotionGoods> result) {
		log.info("更新促销商品信息:{}", result.getAfterData());
		EsPromotionGoods afterData = result.getAfterData();

		// 删除了促销活动商品信息
		if (Objects.equals(1, afterData.getDeleteFlag())) {
			this.removeEsPromotionGoods(result.getAfterData());
		}
	}

	/**
	 * 删除促销商品信息
	 */
	@SneakyThrows
	@Override
	protected void processDeleteInternal(CanalBinLogResult<EsPromotionGoods> result) {
		log.info("删除促销商品信息:{}", result.getBeforeData());
		this.removeEsPromotionGoods(result.getAfterData());
	}

	protected void removeEsPromotionGoods(EsPromotionGoods esPromotionGoods) {
		log.info("开始删除促销商品");

		// es促销活动key为促销类型:促销活动id
		String esPromotionKey = esPromotionGoods.getPromotionType() + StrPool.DASHED
				+ esPromotionGoods.getPromotionId();
		if (PromotionsScopeTypeEnum.ALL.getValue().equals(esPromotionGoods.getScopeType())) {
			log.info("促销商品活动类型为全品类");
			// 如果促销活动是全品类，那么我们更新所有sku
			goodsService.removeEsGoodsIndexAll(esPromotionKey);
		}
		else if (PromotionsScopeTypeEnum.PORTION_GOODS.getValue().equals(esPromotionGoods.getScopeType())) {
			log.info("促销商品活动类型为指定商品");
			// 如果促销活动是指定商品类型,那么促销商品信息里面肯定有skuId 我们更新每个sku
			goodsService.removeEsGoodsIndexPromotion(esPromotionKey, esPromotionGoods.getGoodsId(),
					Collections.singletonList(esPromotionGoods.getSkuId()));
		}
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			throw new FxzException("异常", throwable);
		};
	}

}
