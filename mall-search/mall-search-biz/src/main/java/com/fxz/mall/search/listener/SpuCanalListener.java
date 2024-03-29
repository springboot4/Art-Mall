package com.fxz.mall.search.listener;

import cn.hutool.core.bean.BeanUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.mall.product.dto.GoodsDTO;
import com.fxz.mall.product.entity.Brand;
import com.fxz.mall.product.entity.Category;
import com.fxz.mall.product.feign.RemoteBrandService;
import com.fxz.mall.product.feign.RemoteCategoryService;
import com.fxz.mall.product.feign.RemoteGoodService;
import com.fxz.mall.search.dto.EsGoodsDTO;
import com.fxz.mall.search.entity.EsSpu;
import com.fxz.mall.search.enums.EsIndexEnum;
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

		EsGoodsDTO esGoodsDTO = loadEsGoodsDTO(spuId);

		elasticsearchClient
				.create(c -> c.index(EsIndexEnum.PRODUCT.getValue()).id(spuId.toString()).document(esGoodsDTO));
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

		EsGoodsDTO esGoodsDTO = loadEsGoodsDTO(spuId);

		elasticsearchClient.update(u -> u.index(EsIndexEnum.PRODUCT.getValue()).id(spuId.toString()).doc(esGoodsDTO),
				EsGoodsDTO.class);
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

		elasticsearchClient.delete(d -> d.index(EsIndexEnum.PRODUCT.getValue()).id(spuId.toString()));
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
	private EsGoodsDTO loadEsGoodsDTO(Long spuId) {
		EsGoodsDTO esGoodsDTO = new EsGoodsDTO();

		// 商品信息
		GoodsDTO goodsDTO = remoteGoodService.getSpuDetail(spuId, SecurityConstants.FROM_IN).getData();
		BeanUtil.copyProperties(goodsDTO, esGoodsDTO);
		log.info("商品信息:{}", goodsDTO);

		// 分类信息
		if (Objects.nonNull(goodsDTO.getCategoryId())) {
			Category category = remoteCategoryService.findById(goodsDTO.getCategoryId(), SecurityConstants.FROM_IN)
					.getData();
			esGoodsDTO.setCategoryName(category.getName());
		}

		// 品牌信息
		if (Objects.nonNull(goodsDTO.getBrandId())) {
			Brand brand = remoteBrandService.findById(goodsDTO.getBrandId(), SecurityConstants.FROM_IN).getData();
			esGoodsDTO.setBrandName(brand.getName());
		}
		return esGoodsDTO;
	}

}
