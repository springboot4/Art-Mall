package com.fxz.mall.product.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 锁定库存传输层实体
 *
 * @author fxz
 */
@Data
public class LockStockDTO {

	/**
	 * 订单token
	 */
	private String orderToken;

	/**
	 * 锁定商品列表
	 */
	private List<LockedSku> lockedSkuList;

	@Accessors(chain = true)
	@Data
	public static class LockedSku {

		/**
		 * 商品ID
		 */
		private Long skuId;

		/**
		 * 商品数量
		 */
		private Integer count;

	}

}