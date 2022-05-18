package com.fxz.mall.order.query;

import lombok.Data;

/**
 * 订单分页查询对象
 *
 * @author fxz
 */
@Data
public class OrderPageQuery {

	private int pageNum = 1;

	private int pageSize = 10;

	private Integer status;

	private Long memberId;

	private String orderSn;

	private String beginDate;

	private String endDate;

}
