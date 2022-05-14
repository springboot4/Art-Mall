package com.fxz.mall.order.vo;

import com.fxz.mall.order.dto.OrderItemDto;
import com.fxz.mall.user.dto.AddressDto;
import lombok.Data;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 17:50
 */
@Data
public class OrderConfirmVO {

	/**
	 * 订单token
	 */
	private String orderToken;

	/**
	 * 订单明细
	 */
	private List<OrderItemDto> orderItems;

	/**
	 * 用户地址
	 */
	private List<AddressDto> addresses;

}
