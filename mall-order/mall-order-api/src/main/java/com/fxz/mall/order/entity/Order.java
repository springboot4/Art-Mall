package com.fxz.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@Data
@TableName("order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 订单号
	 */
	private String orderSn;

	/**
	 * 订单总额（分）
	 */
	private Long totalAmount;

	/**
	 * 商品总数
	 */
	private Integer totalQuantity;

	/**
	 * 订单来源[0->PC订单；1->APP订单]
	 */
	private Integer sourceType;

	/**
	 * 订单状态： 101->待付款； 102->用户取消； 103->系统取消； 201->已付款； 202->申请退款； 203->已退款； 301->待发货；
	 * 401->已发货； 501->用户收货； 502->系统收货； 901->已完成；
	 */
	private Integer status;

	/**
	 * 订单备注
	 */
	private String remark;

	/**
	 * 会员id
	 */
	private Long memberId;

	/**
	 * 使用的优惠券
	 */
	private Long couponId;

	/**
	 * 优惠券抵扣金额（分）
	 */
	private Long couponAmount;

	/**
	 * 运费金额（分）
	 */
	private Long freightAmount;

	/**
	 * 应付总额（分）
	 */
	private Long payAmount;

	/**
	 * 支付时间
	 */
	private LocalDateTime payTime;

	/**
	 * 支付方式【1->微信jsapi；2->支付宝；3->余额； 4->微信app；】
	 */
	private Integer payType;

	/**
	 * 微信支付等第三方支付平台的商户订单号
	 */
	private String outTradeNo;

	/**
	 * 微信支付订单号
	 */
	private String transactionId;

	/**
	 * 商户退款单号
	 */
	private String outRefundNo;

	/**
	 * 微信退款单号
	 */
	private String refundId;

	/**
	 * 发货时间
	 */
	private LocalDateTime deliveryTime;

	/**
	 * 确认收货时间
	 */
	private LocalDateTime receiveTime;

	/**
	 * 评价时间
	 */
	private LocalDateTime commentTime;

	/**
	 * 逻辑删除【0->正常；1->已删除】
	 */
	private Integer deleted;

}
