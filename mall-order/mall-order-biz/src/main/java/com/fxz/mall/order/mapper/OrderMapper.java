package com.fxz.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.mall.order.entity.Order;
import com.fxz.mall.order.query.OrderPageQuery;
import com.fxz.mall.order.vo.OrderPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-14
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

	/**
	 * 订单分页列表
	 */
	List<OrderPageVO> listOrderPages(Page<OrderPageVO> page, @Param("queryParams") OrderPageQuery queryParams);

}