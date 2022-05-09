package com.fxz.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.mall.product.entity.Spu;
import com.fxz.mall.product.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {

	/**
	 * 分页查询商品信息
	 */
	List<GoodsVo> listGoods(Page page, @Param("name") String name, @Param("categoryId") Long categoryId);

}