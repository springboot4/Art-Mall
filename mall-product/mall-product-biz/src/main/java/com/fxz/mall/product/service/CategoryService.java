package com.fxz.mall.product.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.CategoryDTO;
import com.fxz.mall.product.entity.Category;

import java.util.List;

/**
 * 商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
public interface CategoryService extends IService<Category> {

	/**
	 * 添加
	 */
	Boolean addCategory(CategoryDTO categoryDTO);

	/**
	 * 修改
	 */
	Boolean updateCategory(CategoryDTO categoryDTO);

	/**
	 * 分页
	 */
	IPage<Category> pageCategory(Page<Category> pageParam, Category category);

	/**
	 * 获取单条
	 */
	Category findById(Long id);

	/**
	 * 获取全部
	 */
	List<Category> findAll();

	/**
	 * 删除
	 */
	Boolean deleteCategory(Long id);

	/**
	 * 查询分类列表
	 * @return 商品分类列表
	 */
	List<Tree<Long>> listCategory(Long parentId);

}