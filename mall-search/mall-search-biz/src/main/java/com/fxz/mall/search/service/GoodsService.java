package com.fxz.mall.search.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fxz.common.es.utils.EsClientUtil;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.product.dto.AttributeValueDTO;
import com.fxz.mall.product.dto.SkuDTO;
import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.query.SpuPageQuery;
import com.fxz.mall.product.vo.GoodsDetailVO;
import com.fxz.mall.product.vo.GoodsPageVO;
import com.fxz.mall.product.vo.GoodsVO;
import com.fxz.mall.promotion.enums.PromotionTypeEnum;
import com.fxz.mall.search.dto.EsGoodsDTO;
import com.fxz.mall.search.entity.EsPage;
import com.fxz.mall.search.entity.EsPromotionGoods;
import com.fxz.mall.search.enums.EsIndexEnum;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/29 10:26
 */
@Service
@RequiredArgsConstructor
public class GoodsService {

	private final ElasticsearchClient elasticsearchClient;

	/**
	 * pc端es分页查询商品信息
	 */
	@SneakyThrows
	public EsPage<GoodsVO> pageGoods(Long current, Long pageSize, String name, Long categoryId) {
		BoolQuery boolQuery = BoolQuery.of(b -> {
			if (Objects.nonNull(name)) {
				Query byName = MatchQuery.of(m -> m.field(EsGoodsDTO.Fields.name).query(name))._toQuery();
				b.should(byName);
			}
			if (Objects.nonNull(categoryId)) {
				Query byCategoryId = MatchQuery.of(m -> m.field(EsGoodsDTO.Fields.categoryId).query(categoryId))
						._toQuery();
				b.must(byCategoryId);
			}
			return b;
		});

		SearchResponse<EsGoodsDTO> product = elasticsearchClient.search(s -> s.index(EsIndexEnum.PRODUCT.getValue())
				.from(current.intValue() - 1).size(pageSize.intValue()).query(q -> q.bool(boolQuery)),
				EsGoodsDTO.class);

		List<GoodsVO> goodsVOs = product.hits().hits().stream().map(h -> {
			EsGoodsDTO source = h.source();
			GoodsVO goodsVO = new GoodsVO();

			BeanUtil.copyProperties(source, goodsVO);
			goodsVO.setAlbum(JSONUtil.toJsonStr(source.getSubPicUrls()));
			goodsVO.setStatus(1);

			return goodsVO;
		}).collect(Collectors.toList());

		EsPage<GoodsVO> esPage = new EsPage<>();
		esPage.setCurrent(current);
		esPage.setSize(pageSize);
		esPage.setTotal(product.hits().total() != null ? product.hits().total().value() : 0L);
		esPage.setRecords(goodsVOs);

		return esPage;
	}

	/**
	 * app端es分页查询商品信息
	 */
	@SneakyThrows
	public EsPage<GoodsPageVO> appSpuPage(SpuPageQuery queryParams) {

		SearchRequest searchRequest = SearchRequest.of(s -> {
			s.index(EsIndexEnum.PRODUCT.getValue()).from(queryParams.getPageNum() - 1).size(queryParams.getPageSize());

			if (queryParams.getSort() != null && queryParams.getSortField() != null
					&& queryParams.getSort().equals("asc")) {
				s.sort(so -> so.field(f -> f.field(queryParams.getSortField()).order(SortOrder.Asc)));
			}
			else if (queryParams.getSort() != null && queryParams.getSortField() != null
					&& queryParams.getSort().equals("desc")) {
				s.sort(so -> so.field(f -> f.field(queryParams.getSortField()).order(SortOrder.Desc)));
			}

			return s;
		});

		SearchResponse<EsGoodsDTO> product = elasticsearchClient.search(searchRequest, EsGoodsDTO.class);

		List<GoodsPageVO> goodsPage = product.hits().hits().stream().map(h -> {
			EsGoodsDTO source = h.source();
			GoodsPageVO goodsPageVO = new GoodsPageVO();

			BeanUtil.copyProperties(source, goodsPageVO);
			return goodsPageVO;
		}).collect(Collectors.toList());

		EsPage<GoodsPageVO> esPage = new EsPage<>();
		esPage.setCurrent((long) queryParams.getPageNum());
		esPage.setSize((long) queryParams.getPageSize());
		esPage.setTotal(product.hits().total() != null ? product.hits().total().value() : 0L);
		esPage.setRecords(goodsPage);

		return esPage;
	}

	/**
	 * app端获取商品详情
	 */
	@SneakyThrows
	public GoodsDetailVO getAppSpuDetail(Long spuId) {
		GoodsDetailVO result = new GoodsDetailVO();

		SearchResponse<EsGoodsDTO> search = elasticsearchClient.search(
				s -> s.index(EsIndexEnum.PRODUCT.getValue())
						.query(q -> q.match(m -> m.field(EsGoodsDTO.Fields.id).query(spuId))).size(1),
				EsGoodsDTO.class);

		if (CollectionUtil.isNotEmpty(search.hits().hits())) {
			EsGoodsDTO source = search.hits().hits().get(0).source();

			GoodsDetailVO.GoodsInfo goodsInfo = new GoodsDetailVO.GoodsInfo();
			BeanUtil.copyProperties(source, goodsInfo);
			List<String> album = new ArrayList<>();
			if (StrUtil.isNotBlank(source.getPicUrl())) {
				album.add(source.getPicUrl());
			}
			if (source.getSubPicUrls() != null && source.getSubPicUrls().length > 0) {
				album.addAll(Arrays.asList(source.getSubPicUrls()));
			}
			goodsInfo.setAlbum(album);
			result.setGoodsInfo(goodsInfo);

			List<AttributeValueDTO> attrList = source.getAttrList();
			List<GoodsDetailVO.Attribute> attributes = attrList.stream().map(attr -> {
				GoodsDetailVO.Attribute attribute = new GoodsDetailVO.Attribute();
				BeanUtil.copyProperties(attr, attribute);
				return attribute;
			}).collect(Collectors.toList());
			result.setAttributeList(attributes);

			List<SkuDTO> skuList = source.getSkuList();
			Set<AttributeValueDTO> specSet = new HashSet<>();
			skuList.forEach(item -> specSet.addAll(item.getSpecValList()));
			List<GoodsDetailVO.Specification> specList = new ArrayList<>();
			// 规格Map [key:"颜色",value:[{id:1,value:"黑"},{id:2,value:"白"}]]
			Map<String, List<AttributeValueDTO>> specValueMap = specSet.stream()
					.collect(Collectors.groupingBy(AttributeValueDTO::getName));
			for (Map.Entry<String, List<AttributeValueDTO>> entry : specValueMap.entrySet()) {
				String specName = entry.getKey();
				List<AttributeValueDTO> specValueSourceList = entry.getValue();

				// 规格映射处理
				GoodsDetailVO.Specification spec = new GoodsDetailVO.Specification();
				spec.setName(specName);
				if (CollectionUtil.isNotEmpty(specValueSourceList)) {
					List<GoodsDetailVO.Specification.Value> specValueList = specValueSourceList.stream().map(item -> {
						GoodsDetailVO.Specification.Value specValue = new GoodsDetailVO.Specification.Value();
						specValue.setId(item.getId());
						specValue.setValue(item.getValue());
						return specValue;
					}).collect(Collectors.toList());
					spec.setValues(specValueList);
					specList.add(spec);
				}
			}
			result.setSpecList(specList);

			List<Sku> list = skuList.stream().map(sku -> {
				Sku s = new Sku();
				BeanUtil.copyProperties(sku, s);

				Map<String, EsPromotionGoods> promotionGoodsMap = JacksonUtil.parseObject(
						sku.getPromotionMapJson() != null ? sku.getPromotionMapJson() : "{}",
						new TypeReference<Map<String, EsPromotionGoods>>() {
						});
				if (promotionGoodsMap != null && promotionGoodsMap.get(PromotionTypeEnum.SECKILL.getValue()) != null) {
					EsPromotionGoods esPromotionGoods = promotionGoodsMap.get(PromotionTypeEnum.SECKILL.getValue());
					s.setPromotion(true);
					s.setOriginPrice(esPromotionGoods.getOriginalPrice());
					s.setPrice(esPromotionGoods.getPrice());
				}
				else {
					s.setPromotion(false);
					s.setOriginPrice(sku.getPrice());
					s.setPrice(sku.getPrice());
				}

				List<AttributeValueDTO> specValList = sku.getSpecValList();
				String specIds = specValList.stream().map(item -> String.valueOf(item.getId()))
						.collect(Collectors.joining("_"));
				s.setSpecIds(specIds);
				return s;
			}).collect(Collectors.toList());
			result.setSkuList(list);

		}

		return result;
	}

	/**
	 * 更新es索引中促销活动的信息
	 * @param goods 促销信息
	 * @param key es促销key
	 * @param goodsId 商品id
	 * @param skuIds 需要更新的商品id
	 */
	@SneakyThrows
	public void updateEsGoodsIndexPromotions(EsPromotionGoods goods, String key, Long goodsId, List<Long> skuIds) {
		// 查询出当前索引的信息
		EsGoodsDTO esGoodsDTO = getGoodsById(goodsId);
		if (Objects.isNull(esGoodsDTO)) {
			return;
		}

		// 过滤出要更新的sku的信息
		List<SkuDTO> list = filterSkuList(esGoodsDTO, skuIds);
		if (CollectionUtil.isEmpty(list)) {
			return;
		}

		// 更新sku促销信息
		list.forEach(skuDTO -> {
			// 要更新的sku中促销活动的信息
			Map<String, EsPromotionGoods> promotionMapJson = JacksonUtil.parseObject(
					skuDTO.getPromotionMapJson() != null ? skuDTO.getPromotionMapJson() : "{}",
					new TypeReference<Map<String, EsPromotionGoods>>() {
					});

			if (MapUtil.isEmpty(promotionMapJson)) {
				promotionMapJson = new HashMap<>(2);
			}

			// 促销活动map添加促销信息
			promotionMapJson.put(key, goods);
			skuDTO.setPromotionMapJson(JacksonUtil.toJsonString(promotionMapJson));
		});

		EsGoodsDTO goodsDTO = new EsGoodsDTO();
		goodsDTO.setId(goodsId);
		goodsDTO.setSkuList(esGoodsDTO.getSkuList());

		updateGoodsById(goodsId, goodsDTO);
	}

	/**
	 * 更新es索引中所有商品的促销信息
	 * @param goods 促销信息
	 * @param key es促销key
	 */
	public void updateEsGoodsIndexAll(EsPromotionGoods goods, String key) {
		ThreadUtil.execAsync(() -> {
			for (int start = 0;; start += 1000) {
				int finalStart = start;
				SearchResponse<EsGoodsDTO> search;
				try {
					search = elasticsearchClient.search(s -> s.index(EsIndexEnum.PRODUCT.getValue())
							.query(q -> q.matchAll(m -> m)).from(finalStart).size(1000), EsGoodsDTO.class);
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}

				List<Hit<EsGoodsDTO>> hits = search.hits().hits();
				if (CollectionUtil.isEmpty(hits)) {
					break;
				}

				// todo 批量更新
				hits.forEach(h -> {
					EsGoodsDTO source = h.source();
					updateEsGoodsIndexPromotions(goods, key, source.getId(),
							source.getSkuList().stream().map(SkuDTO::getId).collect(Collectors.toList()));
				});
			}
		});
	}

	/**
	 * 删除es索引中促销商品的信息
	 * @param key es索引中促销key
	 * @param goodsId 商品id
	 * @param skuIds 要更新的skuId
	 */
	public void removeEsGoodsIndexPromotion(String key, Long goodsId, List<Long> skuIds) {
		// 查询出当前索引的信息
		EsGoodsDTO esGoodsDTO = getGoodsById(goodsId);
		if (Objects.isNull(esGoodsDTO)) {
			return;
		}

		// 过滤出要更新的sku的信息
		List<SkuDTO> list = filterSkuList(esGoodsDTO, skuIds);
		if (CollectionUtil.isEmpty(list)) {
			return;
		}

		list.forEach(skuDTO -> {
			// 要更新的sku中促销活动的信息
			Map<String, EsPromotionGoods> promotionMapJson = JacksonUtil.parseObject(skuDTO.getPromotionMapJson(),
					new TypeReference<Map<String, EsPromotionGoods>>() {
					});

			promotionMapJson.remove(key);
			skuDTO.setPromotionMapJson(JacksonUtil.toJsonString(promotionMapJson));
		});

		EsGoodsDTO goodsDTO = new EsGoodsDTO();
		goodsDTO.setId(goodsId);
		goodsDTO.setSkuList(esGoodsDTO.getSkuList());

		updateGoodsById(goodsId, goodsDTO);
	}

	/**
	 * 删除es索引中所有商品的促销信息
	 * @param key es促销key
	 */
	public void removeEsGoodsIndexAll(String key) {
		ThreadUtil.execAsync(() -> {
			for (int start = 0;; start += 1000) {
				int finalStart = start;
				SearchResponse<EsGoodsDTO> search;
				try {
					search = elasticsearchClient.search(s -> s.index(EsIndexEnum.PRODUCT.getValue())
							.query(q -> q.matchAll(m -> m)).from(finalStart).size(1000), EsGoodsDTO.class);
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}

				List<Hit<EsGoodsDTO>> hits = search.hits().hits();
				if (CollectionUtil.isEmpty(hits)) {
					break;
				}

				// todo 批量更新
				hits.forEach(h -> {
					EsGoodsDTO source = h.source();
					removeEsGoodsIndexPromotion(key, source.getId(),
							source.getSkuList().stream().map(SkuDTO::getId).collect(Collectors.toList()));
				});
			}
		});
	}

	public EsGoodsDTO getGoodsById(Long goodsId) {
		return EsClientUtil.getById(EsIndexEnum.PRODUCT.getValue(), goodsId.toString(), EsGoodsDTO.class);
	}

	private void updateGoodsById(Long goodsId, EsGoodsDTO esGoodsDTO) {
		EsClientUtil.updateById(EsIndexEnum.PRODUCT.getValue(), goodsId.toString(), esGoodsDTO, EsGoodsDTO.class);
	}

	public List<SkuDTO> filterSkuList(EsGoodsDTO esGoodsDTO, List<Long> skuIds) {
		// 获取es中当前索引中sku的信息
		List<SkuDTO> skuList = esGoodsDTO.getSkuList();
		// 获取到要更新的sku的信息
		return skuList.stream().filter(sku -> skuIds.contains(sku.getId())).collect(Collectors.toList());
	}

}
