package com.fxz.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.entity.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

	List<Sku> listSkuBySpuId(@Param("id") Long id);

	/**
	 * 获取商品库存信息
	 */
	SkuInfoDTO getSkuInfo(@Param("skuId") Long skuId);

}