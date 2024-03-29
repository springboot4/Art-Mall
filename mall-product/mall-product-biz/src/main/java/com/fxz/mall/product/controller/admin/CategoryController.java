package com.fxz.mall.product.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.product.dto.CategoryDTO;
import com.fxz.mall.product.entity.Category;
import com.fxz.mall.product.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryServiceImpl categoryService;

	/**
	 * 查询分类列表
	 * @return 商品分类列表
	 */
	@GetMapping("/list")
	public Result<List<Tree<Long>>> list() {
		return Result.success(categoryService.listCategory(null));
	}

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody CategoryDTO categoryDTO) {
		return Result.success(categoryService.addCategory(categoryDTO));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody CategoryDTO categoryDTO) {
		return Result.success(categoryService.updateCategory(categoryDTO));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(categoryService.deleteCategory(id));
	}

	/**
	 * 获取单条
	 */
	@Ojbk
	@GetMapping(value = "/findById")
	public Result<Category> findById(Long id) {
		return Result.success(categoryService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<Category>> findAll() {
		return Result.success(categoryService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Category>> pageCategory(Page<Category> pageParam, Category category) {
		return Result.success(PageResult.success(categoryService.pageCategory(pageParam, category)));
	}

}