package com.fxz.mall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.AttributeDTO;
import com.fxz.mall.product.entity.Attribute;

import java.util.List;

/**
 * 商品属性表
 *
 * @author fxz
 * @date 2022-05-05
 */
public interface AttributeService extends IService<Attribute> {

	/**
	 * 添加
	 */
	Boolean addAttribute(AttributeDTO attributeDTO);

	/**
	 * 修改
	 */
	Boolean updateAttribute(AttributeDTO attributeDTO);

	/**
	 * 分页
	 */
	IPage<Attribute> pageAttribute(Page<Attribute> pageParam, Attribute attribute);

	/**
	 * 获取单条
	 */
	Attribute findById(Long id);

	/**
	 * 获取全部
	 */
	List<Attribute> findAll();

	/**
	 * 删除
	 */
	Boolean deleteAttribute(Long id);

	/**
	 * 批量添加属性
	 */
	Boolean saveAttributeBatch(AttributeDTO attributeDTO);

}