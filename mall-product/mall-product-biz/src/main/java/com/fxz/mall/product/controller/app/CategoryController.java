package com.fxz.mall.product.controller.app;

import cn.hutool.core.lang.tree.Tree;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.product.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 【移动端】商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
@RestController("appCategoryController")
@RequestMapping("/app/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryServiceImpl categoryService;

	/**
	 * 查询分类列表
	 * @return 商品分类列表
	 */
	@Ojbk
	@GetMapping("list")
	public Result<List<Tree<Long>>> list(Long parentId) {
		return Result.success(categoryService.listCategory(parentId));
	}

}