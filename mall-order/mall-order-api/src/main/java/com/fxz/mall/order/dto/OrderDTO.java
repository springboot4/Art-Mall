package com.fxz.mall.order.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单详情表
 *
 * @author fxz
 * @date 2022-05-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class OrderDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String orderSn;

	private Long totalAmount;

	private Integer totalQuantity;

	private Integer sourceType;

	private Integer status;

	private String remark;

	private Long memberId;

	private Long couponId;

	private Long couponAmount;

	private Long freightAmount;

	private Long payAmount;

	private LocalDateTime payTime;

	private Integer payType;

	private String outTradeNo;

	private String transactionId;

	private String outRefundNo;

	private String refundId;

	private LocalDateTime deliveryTime;

	private LocalDateTime receiveTime;

	private LocalDateTime commentTime;

	private Integer deleted;

}