package com.fxz.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.order.entity.OrderItem;
import com.fxz.mall.order.vo.OrderPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单商品信息表
 *
 * @author fxz
 * @date 2022-05-15
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

	List<OrderPageVO.OrderItem> listOrderItemsByOrderId(Long orderId);

}