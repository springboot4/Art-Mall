package com.fxz.mall.search.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.product.dto.AttributeValueDto;
import com.fxz.mall.product.dto.SkuDto;
import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.query.SpuPageQuery;
import com.fxz.mall.product.vo.GoodsDetailVO;
import com.fxz.mall.product.vo.GoodsPageVO;
import com.fxz.mall.product.vo.GoodsVo;
import com.fxz.mall.promotion.enums.PromotionTypeEnum;
import com.fxz.mall.search.dto.EsGoodsDto;
import com.fxz.mall.search.entity.EsPage;
import com.fxz.mall.search.entity.EsPromotionGoods;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

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
	public EsPage<GoodsVo> pageGoods(Long current, Long pageSize, String name, Long categoryId) {
		BoolQuery boolQuery = BoolQuery.of(b -> {
			if (Objects.nonNull(name)) {
				Query byName = MatchQuery.of(m -> m.field("name").query(name))._toQuery();
				b.should(byName);
			}
			if (Objects.nonNull(categoryId)) {
				Query byCategoryId = MatchQuery.of(m -> m.field("categoryId").query(categoryId))._toQuery();
				b.must(byCategoryId);
			}
			return b;
		});

		SearchResponse<EsGoodsDto> product = elasticsearchClient.search(s -> s.index("product")
				.from(current.intValue() - 1).size(pageSize.intValue()).query(q -> q.bool(boolQuery)),
				EsGoodsDto.class);

		List<GoodsVo> goodsVos = product.hits().hits().stream().map(h -> {
			EsGoodsDto source = h.source();
			GoodsVo goodsVo = new GoodsVo();

			BeanUtil.copyProperties(source, goodsVo);
			goodsVo.setAlbum(JSONUtil.toJsonStr(source.getSubPicUrls()));
			goodsVo.setStatus(1);

			return goodsVo;
		}).collect(Collectors.toList());

		EsPage<GoodsVo> esPage = new EsPage<>();
		esPage.setCurrent(current);
		esPage.setSize(pageSize);
		esPage.setTotal(product.hits().total() != null ? product.hits().total().value() : 0L);
		esPage.setRecords(goodsVos);

		return esPage;
	}

	/**
	 * app端es分页查询商品信息
	 */
	@SneakyThrows
	public EsPage<GoodsPageVO> appSpuPage(SpuPageQuery queryParams) {

		SearchRequest searchRequest = SearchRequest.of(s -> {
			s.index("product").from(queryParams.getPageNum() - 1).size(queryParams.getPageSize());

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

		SearchResponse<EsGoodsDto> product = elasticsearchClient.search(searchRequest, EsGoodsDto.class);

		List<GoodsPageVO> goodsPage = product.hits().hits().stream().map(h -> {
			EsGoodsDto source = h.source();
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

		SearchResponse<EsGoodsDto> search = elasticsearchClient.search(
				s -> s.index("product").query(q -> q.match(m -> m.field("id").query(spuId))).size(1), EsGoodsDto.class);

		if (CollectionUtil.isNotEmpty(search.hits().hits())) {
			EsGoodsDto source = search.hits().hits().get(0).source();

			GoodsDetailVO.GoodsInfo goodsInfo = new GoodsDetailVO.GoodsInfo();
			BeanUtil.copyProperties(source, goodsInfo, "album");
			List<String> album = new ArrayList<>();
			if (StrUtil.isNotBlank(source.getPicUrl())) {
				album.add(source.getPicUrl());
			}
			if (source.getSubPicUrls() != null && source.getSubPicUrls().length > 0) {
				album.addAll(Arrays.asList(source.getSubPicUrls()));
			}
			goodsInfo.setAlbum(album);
			result.setGoodsInfo(goodsInfo);

			List<AttributeValueDto> attrList = source.getAttrList();
			List<GoodsDetailVO.Attribute> attributes = attrList.stream().map(attr -> {
				GoodsDetailVO.Attribute attribute = new GoodsDetailVO.Attribute();
				BeanUtil.copyProperties(attr, attribute);
				return attribute;
			}).collect(Collectors.toList());
			result.setAttributeList(attributes);

			List<SkuDto> skuList = source.getSkuList();
			Set<AttributeValueDto> specSet = new HashSet<>();
			skuList.forEach(item -> specSet.addAll(item.getSpecValList()));
			List<GoodsDetailVO.Specification> specList = new ArrayList<>();
			// 规格Map [key:"颜色",value:[{id:1,value:"黑"},{id:2,value:"白"}]]
			Map<String, List<AttributeValueDto>> specValueMap = specSet.stream()
					.collect(Collectors.groupingBy(AttributeValueDto::getName));
			for (Map.Entry<String, List<AttributeValueDto>> entry : specValueMap.entrySet()) {
				String specName = entry.getKey();
				List<AttributeValueDto> specValueSourceList = entry.getValue();

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

				List<AttributeValueDto> specValList = sku.getSpecValList();
				String specIds = specValList.stream().map(item -> String.valueOf(item.getId()))
						.collect(Collectors.joining("_"));
				s.setSpecIds(specIds);
				return s;
			}).collect(Collectors.toList());
			result.setSkuList(list);

		}

		return result;
	}

}
