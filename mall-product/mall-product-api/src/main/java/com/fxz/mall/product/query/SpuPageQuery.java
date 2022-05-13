package com.fxz.mall.product.query;

import lombok.Data;

/**
 * 商品分页查询对象
 *
 * @author fxz
 */

@Data
public class SpuPageQuery {

	private int pageNum = 1;

	private int pageSize = 10;

	private String keywords;

	private Long categoryId;

	private String sortField;

	private String sort;

}
