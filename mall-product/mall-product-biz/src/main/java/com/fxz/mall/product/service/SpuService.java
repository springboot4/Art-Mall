package com.fxz.mall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.GoodsDto;
import com.fxz.mall.product.dto.SpuDto;
import com.fxz.mall.product.entity.Spu;

import java.util.List;

/**
 * 商品表
 *
 * @author fxz
 * @date 2022-05-06
 */
public interface SpuService extends IService<Spu> {

	/**
	 * 添加
	 */
	Boolean addSpu(SpuDto spuDto);

	/**
	 * 修改
	 */
	Boolean updateSpu(SpuDto spuDto);

	/**
	 * 分页
	 */
	IPage<Spu> pageSpu(Page<Spu> pageParam, Spu spu);

	/**
	 * 获取单条
	 */
	Spu findById(Long id);

	/**
	 * 获取全部
	 */
	List<Spu> findAll();

	/**
	 * 删除
	 */
	Boolean deleteSpu(Long id);

	/**
	 * 保存商品
	 * @param goodsDto 商品信息
	 * @return 是否保存成功
	 */
	Boolean addGoods(GoodsDto goodsDto);

}