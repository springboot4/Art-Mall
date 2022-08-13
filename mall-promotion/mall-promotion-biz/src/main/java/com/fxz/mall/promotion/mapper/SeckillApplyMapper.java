package com.fxz.mall.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.promotion.entity.SeckillApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Mapper
public interface SeckillApplyMapper extends BaseMapper<SeckillApply> {

	SeckillApply getSeckillApplyBySeckillId(@Param("seckillId") Long id);

}