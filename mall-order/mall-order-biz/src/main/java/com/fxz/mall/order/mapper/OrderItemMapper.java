package com.fxz.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品信息表
 *
 * @author fxz
 * @date 2022-05-15
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}