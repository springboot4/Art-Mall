package com.fxz.mall.product.dto;

import lombok.Data;

import java.util.List;

/**
 * 商品验价传输层实体
 *
 * @author fxz
 */
@Data
public class CheckPriceDTO {

	/**
	 * 订单商品总金额
	 */
	private Long orderTotalAmount;

	/**
	 * 订单商品明细
	 */
	private List<CheckSku> checkSkus;

	/**
	 * 订单商品明细
	 */
	@Data
	public static class CheckSku {

		/**
		 * skuId
		 */
		private Long skuId;

		/**
		 * 商品数量
		 */
		private Integer count;

	}

}