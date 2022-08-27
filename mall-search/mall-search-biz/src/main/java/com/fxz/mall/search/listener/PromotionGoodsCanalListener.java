package com.fxz.mall.search.listener;

import cn.hutool.core.text.StrPool;
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
import com.fxz.mall.promotion.enums.PromotionsScopeTypeEnum;
import com.fxz.mall.search.dto.EsGoodsDTO;
import com.fxz.mall.search.entity.EsPromotionGoods;
import com.fxz.mall.search.service.GoodsService;
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

    private final GoodsService goodsService;

    /**
     * 新增促销商品信息
     */
    @SneakyThrows
    @Override
    protected void processInsertInternal(CanalBinLogResult<EsPromotionGoods> result) {
        log.info("新增促销商品信息:{}", result);

        // 促销商品信息
        EsPromotionGoods afterData = result.getAfterData();
        // es促销活动key为促销类型:促销活动id
        String esPromotionKey = afterData.getPromotionType() + StrPool.DASHED + afterData.getPromotionId();

        if (PromotionsScopeTypeEnum.ALL.getValue().equals(afterData.getScopeType())) {
            log.info("促销商品活动类型为全品类");

            // 如果促销活动是全品类，那么我们更新所有sku
            goodsService.updateEsGoodsIndexAll(afterData, esPromotionKey);
        } else if (PromotionsScopeTypeEnum.PORTION_GOODS_CATEGORY.getValue().equals(afterData.getScopeType())) {
            log.info("促销商品活动类型为部分商品分类");
            // 如果促销活动是部分商品分类，那么促销商品信息里面肯定有分类id 我们更新分类下的每个sku
        } else if (PromotionsScopeTypeEnum.PORTION_GOODS.getValue().equals(afterData.getScopeType())) {
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
