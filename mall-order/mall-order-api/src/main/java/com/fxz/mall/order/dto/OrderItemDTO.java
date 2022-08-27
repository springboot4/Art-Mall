package com.fxz.mall.order.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单商品信息表
 *
 * @author fxz
 * @date 2022-05-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class OrderItemDTO extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long orderId;

	private String spuName;

	private Long skuId;

	private String skuSn;

	private String skuName;

	private String picUrl;

	private Long price;

	private Integer count;

	private Long totalAmount;

	private Integer deleted;

}