package com.fxz.mall.product.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.dto.AttributeDto;
import com.fxz.mall.product.entity.Attribute;
import com.fxz.mall.product.service.impl.AttributeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性表
 *
 * @author fxz
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/attribute")
@RequiredArgsConstructor
public class AttributeController {

	private final AttributeServiceImpl attributeService;

	/**
	 * 批量添加属性
	 */
	@PostMapping(value = "/saveAttributeBatch")
	public Result<Boolean> saveAttributeBatch(@RequestBody AttributeDto attributeDto) {
		return Result.judge(attributeService.saveAttributeBatch(attributeDto));
	}

	/**
	 * 根据分类id和属性类型获取属性列表
	 * @param categoryId 分类id
	 * @param type 属性类型
	 * @return 属性列表
	 */
	@GetMapping(value = "/listAttributes")
	public Result<List<Attribute>> listAttributes(Long categoryId, Integer type) {
		List<Attribute> list = attributeService
				.list(Wrappers.<Attribute>lambdaQuery().eq(categoryId != null, Attribute::getCategoryId, categoryId)
						.eq(type != null, Attribute::getType, type));
		return Result.success(list);
	}

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody AttributeDto attributeDto) {
		return Result.judge(attributeService.addAttribute(attributeDto));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody AttributeDto attributeDto) {
		return Result.success(attributeService.updateAttribute(attributeDto));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(attributeService.deleteAttribute(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<Attribute> findById(Long id) {
		return Result.success(attributeService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<Attribute>> findAll() {
		return Result.success(attributeService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Attribute>> pageAttribute(Page<Attribute> pageParam, Attribute attribute) {
		return Result.success(PageResult.success(attributeService.pageAttribute(pageParam, attribute)));
	}

}