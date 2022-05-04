package com.fxz.mall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.BrandDto;
import com.fxz.mall.product.entity.Brand;

import java.util.List;

/**
 * 商品品牌表
 *
 * @author fxz
 * @date 2022-05-04
 */
public interface BrandService extends IService<Brand> {

	/**
	 * 添加
	 */
	Boolean addBrand(BrandDto brandDto);

	/**
	 * 修改
	 */
	Boolean updateBrand(BrandDto brandDto);

	/**
	 * 分页
	 */
	IPage<Brand> pageBrand(Page<Brand> pageParam, Brand brand);

	/**
	 * 获取单条
	 */
	Brand findById(Long id);

	/**
	 * 获取全部
	 */
	List<Brand> findAll();

	/**
	 * 删除
	 */
	Boolean deleteBrand(Long id);

}