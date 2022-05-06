package com.fxz.mall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.dto.AttributeValueDto;
import com.fxz.mall.product.entity.AttributeValue;
import com.fxz.mall.product.mapper.AttributeValueMapper;
import com.fxz.mall.product.service.AttributeValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性项表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttributeValueServiceImpl extends ServiceImpl<AttributeValueMapper, AttributeValue>
		implements AttributeValueService {

	private final AttributeValueMapper attributeValueMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addAttributeValue(AttributeValueDto attributeValueDto) {
		AttributeValue attributeValue = new AttributeValue();
		BeanUtils.copyProperties(attributeValueDto, attributeValue);
		attributeValueMapper.insert(attributeValue);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateAttributeValue(AttributeValueDto attributeValueDto) {
		AttributeValue attributeValue = new AttributeValue();
		BeanUtils.copyProperties(attributeValueDto, attributeValue);
		attributeValueMapper.updateById(attributeValue);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<AttributeValue> pageAttributeValue(Page<AttributeValue> pageParam, AttributeValue attributeValue) {
		return attributeValueMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public AttributeValue findById(Long id) {
		return attributeValueMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<AttributeValue> findAll() {
		return attributeValueMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteAttributeValue(Long id) {
		attributeValueMapper.deleteById(id);
		return Boolean.TRUE;
	}

}