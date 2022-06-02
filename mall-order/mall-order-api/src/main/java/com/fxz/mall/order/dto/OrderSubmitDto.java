package com.fxz.mall.order.dto;

import com.fxz.mall.member.dto.AddressDto;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/15 09:18
 */
@Data
public class OrderSubmitDto {

	/**
	 * 提交订单确认页面签发的令牌
	 */
	private String orderToken;

	/**
	 * 订单总金额-用于验价(单位：分)
	 */
	private Long totalAmount;

	/**
	 * 支付金额(单位：分)
	 */
	private Long payAmount;

	/**
	 * 订单的商品明细
	 */
	private List<OrderItemDto> orderItems;

	/**
	 * 订单备注
	 */
	@Size(max = 500, message = "订单备注长度不能超过500")
	private String remark;

	/**
	 * 优惠券ID
	 */
	private String couponId;

	/**
	 * 收获地址
	 */
	private AddressDto deliveryAddress;

	/**
	 * 订单来源
	 */
	private Integer sourceType;

}
