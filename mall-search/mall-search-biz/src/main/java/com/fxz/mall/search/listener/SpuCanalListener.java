package com.fxz.mall.search.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.core.exception.FxzException;
import com.fxz.mall.product.dto.GoodsDto;
import com.fxz.mall.product.feign.RemoteGoodService;
import com.fxz.mall.search.entity.Spu;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 监听数据库商品信息的变化
 *
 * @author fxz
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SpuCanalListener extends BaseCanalBinlogEventProcessor<Spu> {

	private final ElasticsearchClient elasticsearchClient;

	private final RemoteGoodService remoteGoodService;

	/**
	 * 插入
	 */
	@SneakyThrows
	@Override
	protected void processInsertInternal(CanalBinLogResult<Spu> result) {
		log.info("数据库插入商品:{}", result);

		// spuId
		Long spuId = result.getPrimaryKey();

		// 商品信息
		GoodsDto goodsDto = remoteGoodService.getSpuDetail(spuId, SecurityConstants.FROM_IN).getData();
		log.info("商品信息:{}", goodsDto);

		elasticsearchClient.create(c -> c.index("product").id(spuId.toString()).document(goodsDto));
	}

	/**
	 * 更新
	 */
	@SneakyThrows
	@Override
	protected void processUpdateInternal(CanalBinLogResult<Spu> result) {
		log.info("更新商品:{}", result);

		// spuId
		Long spuId = result.getPrimaryKey();

		// 商品信息
		GoodsDto goodsDto = remoteGoodService.getSpuDetail(spuId, SecurityConstants.FROM_IN).getData();
		log.info("商品信息:{}", goodsDto);

		elasticsearchClient.update(u -> u.index("product").id(spuId.toString()).doc(goodsDto), GoodsDto.class);
	}

	/**
	 * 删除
	 */
	@SneakyThrows
	@Override
	protected void processDeleteInternal(CanalBinLogResult<Spu> result) {
		log.info("删除商品:{}", result);

		// spuId
		Long spuId = result.getPrimaryKey();

		elasticsearchClient.delete(d -> d.index("product").id(spuId.toString()));
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			throw new FxzException("异常", throwable);
		};
	}

}
