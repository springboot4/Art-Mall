package com.fxz.mall.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.dto.AttributeDTO;
import com.fxz.mall.product.entity.Attribute;
import com.fxz.mall.product.mapper.AttributeMapper;
import com.fxz.mall.product.service.AttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品属性表
 *
 * @author fxz
 * @date 2022-05-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService {

	private final AttributeMapper attributeMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addAttribute(AttributeDTO attributeDTO) {
		Attribute attribute = new Attribute();
		BeanUtils.copyProperties(attributeDTO, attribute);
		attributeMapper.insert(attribute);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateAttribute(AttributeDTO attributeDTO) {
		Attribute attribute = new Attribute();
		BeanUtils.copyProperties(attributeDTO, attribute);
		attributeMapper.updateById(attribute);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Attribute> pageAttribute(Page<Attribute> pageParam, Attribute attribute) {
		return attributeMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Attribute findById(Long id) {
		return attributeMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Attribute> findAll() {
		return attributeMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteAttribute(Long id) {
		attributeMapper.deleteById(id);
		return Boolean.TRUE;
	}

	/**
	 * 批量添加属性
	 */
	@Override
	public Boolean saveAttributeBatch(AttributeDTO attributeDTO) {
		// 此次保存属性所属的分类
		Long categoryId = attributeDTO.getCategoryId();
		// 此次保存属性所属的类型
		Integer type = attributeDTO.getType();

		// 过滤出此次保存存在id的属性
		List<Long> formIds = attributeDTO.getAttributes().stream().map(Attribute::getId).filter(Objects::nonNull)
				.collect(Collectors.toList());

		// 数据库中此分类下此类型的所有属性
		List<Long> dbIds = attributeMapper
				.selectList(Wrappers.<Attribute>lambdaQuery().eq(Attribute::getCategoryId, categoryId)
						.eq(Attribute::getType, type).select(Attribute::getId))
				.stream().map(Attribute::getId).collect(Collectors.toList());

		// 删除此次表单没有的属性ID
		if (CollectionUtil.isNotEmpty(dbIds)) {
			List<Long> rmIds = dbIds.stream().filter(id -> CollectionUtil.isEmpty(formIds) || !formIds.contains(id))
					.collect(Collectors.toList());
			if (CollectionUtil.isNotEmpty(rmIds)) {
				attributeMapper.deleteBatchIds(rmIds);
			}
		}

		// 新增/修改表单提交的属性
		List<Attribute> formAttributes = attributeDTO.getAttributes();
		List<Attribute> attributeList = new ArrayList<>();
		formAttributes.forEach(item -> attributeList.add(
				new Attribute().setId(item.getId()).setCategoryId(categoryId).setType(type).setName(item.getName())));

		return this.saveOrUpdateBatch(attributeList);
	}

}