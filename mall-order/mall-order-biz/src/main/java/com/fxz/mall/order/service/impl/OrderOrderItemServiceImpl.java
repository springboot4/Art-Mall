package com.fxz.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.order.entity.OrderItem;
import com.fxz.mall.order.mapper.OrderItemMapper;
import com.fxz.mall.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单商品信息表
 *
 * @author fxz
 * @date 2022-05-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderOrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}