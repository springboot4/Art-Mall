package com.fxz.mall.product.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.dto.BrandDto;
import com.fxz.mall.product.entity.Brand;
import com.fxz.mall.product.service.impl.BrandServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌表
 *
 * @author fxz
 * @date 2022-05-04
 */
@Slf4j
@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

	private final BrandServiceImpl brandService;

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody BrandDto brandDto) {
		return Result.success(brandService.addBrand(brandDto));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody BrandDto brandDto) {
		return Result.success(brandService.updateBrand(brandDto));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(brandService.deleteBrand(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<Brand> findById(Long id) {
		return Result.success(brandService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<Brand>> findAll() {
		return Result.success(brandService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Brand>> pageBrand(Page<Brand> pageParam, Brand brand) {
		return Result.success(PageResult.success(brandService.pageBrand(pageParam, brand)));
	}

}