package com.fxz.mall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.CheckPriceDTO;
import com.fxz.mall.product.dto.LockStockDTO;
import com.fxz.mall.product.dto.SkuDto;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.entity.Sku;

import java.util.List;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
public interface SkuService extends IService<Sku> {

	/**
	 * 添加
	 */
	Boolean addSku(SkuDto skuDto);

	/**
	 * 修改
	 */
	Boolean updateSku(SkuDto skuDto);

	/**
	 * 分页
	 */
	IPage<Sku> pageSku(Page<Sku> pageParam, Sku sku);

	/**
	 * 获取单条
	 */
	Sku findById(Long id);

	/**
	 * 获取全部
	 */
	List<Sku> findAll();

	/**
	 * 删除
	 */
	Boolean deleteSku(Long id);

	/**
	 * 获取商品库存信息
	 */
	SkuInfoDTO getSkuInfo(Long skuId);

	/**
	 * 商品验价
	 * @param checkPriceDTO 校验价格dto
	 * @return 价格是否相同
	 */
	Boolean checkPrice(CheckPriceDTO checkPriceDTO);

	/**
	 * 锁定库存
	 * @return 锁定是否成功
	 */
	Boolean lockStock(LockStockDTO lockStockDTO);

	/**
	 * 扣减库存 - 支付成功
	 */
	Boolean deductStock(String orderToken);

}