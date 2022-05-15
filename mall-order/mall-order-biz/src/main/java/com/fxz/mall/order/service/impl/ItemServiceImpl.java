package com.fxz.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.order.entity.Item;
import com.fxz.mall.order.mapper.ItemMapper;
import com.fxz.mall.order.service.ItemService;
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
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}