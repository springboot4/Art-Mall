package com.fxz.mall.search.listener;

import cn.hutool.core.bean.BeanUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.mall.product.dto.GoodsDto;
import com.fxz.mall.product.entity.Brand;
import com.fxz.mall.product.entity.Category;
import com.fxz.mall.product.feign.RemoteBrandService;
import com.fxz.mall.product.feign.RemoteCategoryService;
import com.fxz.mall.product.feign.RemoteGoodService;
import com.fxz.mall.search.dto.EsGoodsDto;
import com.fxz.mall.search.entity.EsSpu;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 监听数据库商品信息的变化，同步商品信息到es
 *
 * @author fxz
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SpuCanalListener extends BaseCanalBinlogEventProcessor<EsSpu> {

	private final ElasticsearchClient elasticsearchClient;

	private final RemoteGoodService remoteGoodService;

	private final RemoteCategoryService remoteCategoryService;

	private final RemoteBrandService remoteBrandService;

	/**
	 * 新增商品信息到es
	 */
	@SneakyThrows
	@Override
	protected void processInsertInternal(CanalBinLogResult<EsSpu> result) {
		log.info("数据库插入商品:{}", result);

		// spuId
		Long spuId = result.getPrimaryKey();

		EsGoodsDto esGoodsDto = loadEsGoodsDto(spuId);

		elasticsearchClient.create(c -> c.index("product").id(spuId.toString()).document(esGoodsDto));
	}

	/**
	 * 更新商品信息到es
	 */
	@SneakyThrows
	@Override
	protected void processUpdateInternal(CanalBinLogResult<EsSpu> result) {
		log.info("更新商品:{}", result);

		// spuId
		Long spuId = result.getPrimaryKey();

		EsGoodsDto esGoodsDto = loadEsGoodsDto(spuId);

		elasticsearchClient.update(u -> u.index("product").id(spuId.toString()).doc(esGoodsDto), EsGoodsDto.class);
	}

	/**
	 * 删除es中的商品信息
	 */
	@SneakyThrows
	@Override
	protected void processDeleteInternal(CanalBinLogResult<EsSpu> result) {
		log.info("删除商品:{}", result);

		// spuId
		Long spuId = result.getPrimaryKey();

		elasticsearchClient.delete(d -> d.index("product").id(spuId.toString()));
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			log.info("异常信息:{}", throwable.getLocalizedMessage());
		};
	}

	/**
	 * 根据spuId组装商品信息
	 */
	private EsGoodsDto loadEsGoodsDto(Long spuId) {
		EsGoodsDto esGoodsDto = new EsGoodsDto();

		// 商品信息
		GoodsDto goodsDto = remoteGoodService.getSpuDetail(spuId, SecurityConstants.FROM_IN).getData();
		BeanUtil.copyProperties(goodsDto, esGoodsDto);
		log.info("商品信息:{}", goodsDto);

		// 分类信息
		if (Objects.nonNull(goodsDto.getCategoryId())) {
			Category category = remoteCategoryService.findById(goodsDto.getCategoryId(), SecurityConstants.FROM_IN)
					.getData();
			esGoodsDto.setCategoryName(category.getName());
		}

		// 品牌信息
		if (Objects.nonNull(goodsDto.getBrandId())) {
			Brand brand = remoteBrandService.findById(goodsDto.getBrandId(), SecurityConstants.FROM_IN).getData();
			esGoodsDto.setBrandName(brand.getName());
		}
		return esGoodsDto;
	}

}
