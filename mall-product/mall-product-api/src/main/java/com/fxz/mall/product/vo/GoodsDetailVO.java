package com.fxz.mall.product.vo;

import com.fxz.common.core.serializer.ImgUrl;
import com.fxz.mall.product.entity.Sku;
import lombok.Data;

import java.util.List;

/**
 * 商品详情视图对象
 *
 * @author fxz
 * @date 2021/5/13
 */
@Data
public class GoodsDetailVO {

	private GoodsInfo goodsInfo;

	private List<Attribute> attributeList;

	private List<Specification> specList;

	private List<Sku> skuList;

	@Data
	public static class GoodsInfo {

		private Long id;

		private String name;

		private Long originPrice;

		private Long price;

		private Integer sales;

		@ImgUrl
		private List<String> album;

		private String detail;

	}

	@Data
	public static class Attribute {

		private Long id;

		private String name;

		private String value;

	}

	@Data
	public static class Specification {

		private String name;

		private List<Value> values;

		@Data
		public static class Value {

			private Long id;

			private String value;

		}

	}

}
