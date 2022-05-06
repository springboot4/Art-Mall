package com.fxz.mall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.AttributeValueDto;
import com.fxz.mall.product.entity.AttributeValue;

import java.util.List;

/**
 * 商品属性项表
 *
 * @author fxz
 * @date 2022-05-06
 */
public interface AttributeValueService extends IService<AttributeValue> {

	/**
	 * 添加
	 */
	Boolean addAttributeValue(AttributeValueDto attributeValueDto);

	/**
	 * 修改
	 */
	Boolean updateAttributeValue(AttributeValueDto attributeValueDto);

	/**
	 * 分页
	 */
	IPage<AttributeValue> pageAttributeValue(Page<AttributeValue> pageParam, AttributeValue attributeValue);

	/**
	 * 获取单条
	 */
	AttributeValue findById(Long id);

	/**
	 * 获取全部
	 */
	List<AttributeValue> findAll();

	/**
	 * 删除
	 */
	Boolean deleteAttributeValue(Long id);

}