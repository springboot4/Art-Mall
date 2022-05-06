package com.fxz.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.product.entity.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

}