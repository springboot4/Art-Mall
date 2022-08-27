package com.fxz.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.dto.AttributeValueDTO;
import com.fxz.mall.product.dto.GoodsDTO;
import com.fxz.mall.product.dto.SkuDTO;
import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.entity.SkuAttributeValue;
import com.fxz.mall.product.entity.Spu;
import com.fxz.mall.product.entity.SpuAttributeValue;
import com.fxz.mall.product.enums.AttributeTypeEnum;
import com.fxz.mall.product.mapper.SpuMapper;
import com.fxz.mall.product.query.SpuPageQuery;
import com.fxz.mall.product.service.SpuService;
import com.fxz.mall.product.vo.GoodsDetailVO;
import com.fxz.mall.product.vo.GoodsPageVO;
import com.fxz.mall.product.vo.GoodsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品表
 *
 * @author fxz
 * @date 2022-05-06
 */
@SuppressWarnings("all")
@Slf4j
@Service
@RequiredArgsConstructor
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

	private final SpuMapper spuMapper;

	private final SkuServiceImpl skuService;

	private final SpuAttributeValueServiceImpl spuAttributeValueService;

	private final SkuAttributeValueServiceImpl skuAttributeValueService;

	/**
	 * 保存商品
	 * @param goodsDTO 商品信息
	 * @return 是否保存成功
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean addGoods(GoodsDTO goodsDTO) {
		// 保存spu信息
		Long goodsId = this.saveSpu(goodsDTO);

		// 属性保存
		List<AttributeValueDTO> attrValList = goodsDTO.getAttrList();
		this.saveAttribute(goodsId, attrValList);

		// sku保存
		List<SkuDTO> skuList = goodsDTO.getSkuList();

		return this.saveSku(goodsId, skuList);
	}

	/**
	 * 分页查询商品信息
	 */
	@Override
	public IPage<GoodsVO> listGoods(Page page, String name, Long categoryId) {
		List<GoodsVO> goodsVOs = spuMapper.listGoods(page, name, categoryId);
		return page.setRecords(goodsVOs);
	}

	/**
	 * 删除商品信息
	 */
	@Override
	public Boolean delete(Long id) {

		List<Long> skuIds = skuService.list(Wrappers.<Sku>lambdaQuery().eq(Sku::getSpuId, id).select(Sku::getId))
				.stream().map(Sku::getId).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(skuIds)) {
			// 删除sku相关属性
			skuAttributeValueService.remove(Wrappers.<SkuAttributeValue>lambdaQuery()
					.eq(SkuAttributeValue::getSpuId, id).in(SkuAttributeValue::getSkuId, skuIds));
			// 删除sku
			skuService.remove(Wrappers.<Sku>lambdaQuery().in(Sku::getId, skuIds));
		}

		// 删除spu相关属性
		spuAttributeValueService.remove(Wrappers.<SpuAttributeValue>lambdaQuery().eq(SpuAttributeValue::getSpuId, id));

		// 删除spu
		return this.removeById(id);
	}

	/**
	 * app端分页查询spu
	 */
	@Override
	public IPage<GoodsPageVO> listAppSpuPage(SpuPageQuery queryParams) {
		Page<GoodsPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
		List<GoodsPageVO> list = this.baseMapper.listAppSpuPage(page, queryParams);
		page.setRecords(list);
		return page;
	}

	/**
	 * app端获取商品详情
	 */
	@Override
	public GoodsDetailVO getAppSpuDetail(Long spuId) {
		Spu spu = this.getById(spuId);
		Assert.isTrue(spu != null, "商品不存在");

		GoodsDetailVO goodsDetailVO = new GoodsDetailVO();

		// 商品基本信息
		GoodsDetailVO.GoodsInfo goodsInfo = new GoodsDetailVO.GoodsInfo();
		BeanUtil.copyProperties(spu, goodsInfo, "album");

		List<String> album = new ArrayList<>();

		if (StrUtil.isNotBlank(spu.getPicUrl())) {
			album.add(spu.getPicUrl());
		}
		if (spu.getAlbum() != null && spu.getAlbum().length() > 0) {
			String[] strings = JSONUtil.parseArray(spu.getAlbum()).toArray(new String[0]);
			album.addAll(Arrays.asList(strings));
			goodsInfo.setAlbum(album);
		}
		goodsDetailVO.setGoodsInfo(goodsInfo);

		// 商品属性列表
		List<GoodsDetailVO.Attribute> attributeList = spuAttributeValueService
				.list(new LambdaQueryWrapper<SpuAttributeValue>()
						.eq(SpuAttributeValue::getType, AttributeTypeEnum.ATTRIBUTE.getValue())
						.eq(SpuAttributeValue::getSpuId, spuId)
						.select(SpuAttributeValue::getId, SpuAttributeValue::getName, SpuAttributeValue::getValue))
				.stream().map(item -> {
					GoodsDetailVO.Attribute attribute = new GoodsDetailVO.Attribute();
					BeanUtil.copyProperties(item, attribute);
					return attribute;
				}).collect(Collectors.toList());
		goodsDetailVO.setAttributeList(attributeList);

		// 商品规格列表
		List<SkuAttributeValue> specSourceList = skuAttributeValueService
				.list(new LambdaQueryWrapper<SkuAttributeValue>()
						.eq(SkuAttributeValue::getType, AttributeTypeEnum.SPECIFICATION.getValue())
						.eq(SkuAttributeValue::getSpuId, spuId).select(SkuAttributeValue::getId,
								SkuAttributeValue::getName, SkuAttributeValue::getValue, SkuAttributeValue::getSkuId));

		List<GoodsDetailVO.Specification> specList = new ArrayList<>();
		// 规格Map [key:"颜色",value:[{id:1,value:"黑"},{id:2,value:"白"}]]
		Map<String, List<SkuAttributeValue>> specValueMap = specSourceList.stream()
				.collect(Collectors.groupingBy(SkuAttributeValue::getName));

		for (Map.Entry<String, List<SkuAttributeValue>> entry : specValueMap.entrySet()) {
			String specName = entry.getKey();
			List<SkuAttributeValue> specValueSourceList = entry.getValue();

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
		goodsDetailVO.setSpecList(specList);

		// sku 对应的 规格
		Map<Long, List<SkuAttributeValue>> specBySkuIdMap = specSourceList.stream()
				.collect(Collectors.groupingBy(SkuAttributeValue::getSkuId));

		// 商品SKU列表
		List<Sku> skuSourceList = skuService.list(new LambdaQueryWrapper<Sku>().eq(Sku::getSpuId, spuId)).stream()
				.map(item -> {
					List<SkuAttributeValue> skuAttributeValues = specBySkuIdMap.get(item.getId());
					if (Objects.nonNull(skuAttributeValues)) {
						String sprcIds = skuAttributeValues.stream().map(attr -> {
							return String.valueOf(attr.getId());
						}).collect(Collectors.joining("_"));
						item.setSpecIds(sprcIds);
					}
					return item;
				}).collect(Collectors.toList());
		goodsDetailVO.setSkuList(skuSourceList);

		// todo 添加用户浏览历史记录

		return goodsDetailVO;
	}

	/**
	 * 根据spuId获取商品详情
	 */
	@Override
	public GoodsDTO getSpuDetail(Long spuId) {
		GoodsDTO goodsDTO = new GoodsDTO();

		Spu spu = spuMapper.selectById(spuId);
		BeanUtil.copyProperties(spu, goodsDTO);
		String[] strings = JSONUtil.parseArray(spu.getAlbum()).toArray(new String[0]);
		goodsDTO.setSubPicUrls(strings);

		List<AttributeValueDTO> spuAttributeValue = spuAttributeValueService
				.list(Wrappers.<SpuAttributeValue>lambdaQuery().eq(SpuAttributeValue::getSpuId, spuId)).stream()
				.map(item -> {
					AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
					BeanUtil.copyProperties(item, attributeValueDTO);
					return attributeValueDTO;
				}).collect(Collectors.toList());
		goodsDTO.setAttrList(spuAttributeValue);

		List<SkuDTO> skuDTOList = skuService.list(Wrappers.<Sku>lambdaQuery().eq(Sku::getSpuId, spuId)).stream()
				.map(item -> {
					SkuDTO skuDTO = new SkuDTO();
					BeanUtil.copyProperties(item, skuDTO);
					return skuDTO;
				}).collect(Collectors.toList());
		if (CollectionUtil.isNotEmpty(skuDTOList)) {
			skuDTOList.forEach(skuDTO -> {
				Long skuId = skuDTO.getId();
				List<AttributeValueDTO> skuAttributeValue = skuAttributeValueService
						.list(Wrappers.<SkuAttributeValue>lambdaQuery().eq(SkuAttributeValue::getSkuId, skuId)).stream()
						.map(item -> {
							AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
							BeanUtil.copyProperties(item, attributeValueDTO);
							return attributeValueDTO;
						}).collect(Collectors.toList());
				skuDTO.setSpecValList(skuAttributeValue);
			});
		}
		goodsDTO.setSkuList(skuDTOList);

		return goodsDTO;
	}

	/**
	 * 保存Sku信息
	 * @param goodsId spuId
	 * @param skuList sku列表
	 * @return 是否保存成功
	 */
	private Boolean saveSku(Long goodsId, List<SkuDTO> skuList) {
		// 新增/修改SKU
		skuList.forEach(skuDTO -> {
			Sku sku = new Sku();
			BeanUtils.copyProperties(skuDTO, sku);
			sku.setSpuId(goodsId);

			// 保存sku信息
			skuService.save(sku);

			// 保存sku属性
			List<AttributeValueDTO> specValList = skuDTO.getSpecValList();
			if (CollectionUtils.isNotEmpty(specValList)) {
				List<SkuAttributeValue> skuAttributeValues = specValList.stream().map(item -> {
					SkuAttributeValue skuAttributeValue = new SkuAttributeValue();
					BeanUtils.copyProperties(item, skuAttributeValue);
					skuAttributeValue.setSpuId(goodsId);
					skuAttributeValue.setSkuId(sku.getId());
					return skuAttributeValue;
				}).collect(Collectors.toList());
				skuAttributeValueService.saveOrUpdateBatch(skuAttributeValues);
			}

		});

		return Boolean.TRUE;
	}

	/**
	 * 保存spu的属性
	 * @param goodsId spuId
	 * @param attrValList 属性列表
	 */
	private void saveAttribute(Long goodsId, List<AttributeValueDTO> attrValList) {
		if (CollectionUtils.isNotEmpty(attrValList)) {
			List<SpuAttributeValue> spuAttributeValueList = attrValList.stream().map(item -> {
				SpuAttributeValue spuAttributeValue = new SpuAttributeValue();
				BeanUtils.copyProperties(item, spuAttributeValue);
				spuAttributeValue.setSpuId(goodsId);
				spuAttributeValue.setType(AttributeTypeEnum.ATTRIBUTE.getValue());
				return spuAttributeValue;
			}).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(spuAttributeValueList)) {
				spuAttributeValueService.saveOrUpdateBatch(spuAttributeValueList);
			}
		}
	}

	/**
	 * 保存sku
	 * @param goodsDTO 商品信息
	 * @return spuId
	 */
	private Long saveSpu(GoodsDTO goodsDTO) {
		Spu spu = new Spu();
		BeanUtil.copyProperties(goodsDTO, spu);
		// 商品图册
		spu.setAlbum(JSONUtil.toJsonStr(goodsDTO.getSubPicUrls()));
		boolean result = this.saveOrUpdate(spu);
		return result ? spu.getId() : 0;
	}

}