package com.fxz.mall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.product.dto.GoodsDTO;
import com.fxz.mall.product.entity.Spu;
import com.fxz.mall.product.query.SpuPageQuery;
import com.fxz.mall.product.vo.GoodsPageVO;
import com.fxz.mall.product.vo.GoodsVO;

/**
 * 商品表
 *
 * @author fxz
 * @date 2022-05-06
 */
public interface SpuService extends IService<Spu> {

	/**
	 * 保存商品
	 * @param goodsDTO 商品信息
	 * @return 是否保存成功
	 */
	Boolean addGoods(GoodsDTO goodsDTO);

	/**
	 * 分页查询商品信息
	 */
	IPage<GoodsVO> listGoods(Page objectPage, String name, Long categoryId);

	/**
	 * 删除商品信息
	 */
	Boolean delete(Long id);

	/**
	 * app端分页查询spu
	 */
	IPage<GoodsPageVO> listAppSpuPage(SpuPageQuery queryParams);

	/**
	 * 根据spuId获取商品详情
	 */
	GoodsDTO getSpuDetail(Long spuId);

}