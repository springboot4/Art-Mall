package com.fxz.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-14
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}