package com.fxz.mall.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.order.dto.OrderDto;
import com.fxz.mall.order.entity.Order;
import com.fxz.mall.order.vo.OrderConfirmVO;

import java.util.List;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-14
 */
public interface OrderService extends IService<Order> {

	/**
	 * 添加
	 */
	Boolean addOrder(OrderDto orderDto);

	/**
	 * 修改
	 */
	Boolean updateOrder(OrderDto orderDto);

	/**
	 * 分页
	 */
	IPage<Order> pageOrder(Page<Order> pageParam, Order order);

	/**
	 * 获取单条
	 */
	Order findById(Long id);

	/**
	 * 获取全部
	 */
	List<Order> findAll();

	/**
	 * 删除
	 */
	Boolean deleteOrder(Long id);

	/**
	 * 订单确认 → 进入创建订单页面
	 * <p>
	 * 获取购买商品明细、用户默认收货地址、防重提交唯一token 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
	 * @param skuId 直接购买必填，购物车结算不填
	 * @return OrderConfirmVO
	 */
	OrderConfirmVO confirm(Long skuId);

}