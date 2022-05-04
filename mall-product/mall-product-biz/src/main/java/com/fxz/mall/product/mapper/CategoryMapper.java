package com.fxz.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.product.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}