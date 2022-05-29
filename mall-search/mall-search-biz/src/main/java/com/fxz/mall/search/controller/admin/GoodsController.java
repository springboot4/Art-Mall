package com.fxz.mall.search.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.vo.GoodsVo;
import com.fxz.mall.search.entity.EsPage;
import com.fxz.mall.search.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/29 10:25
 */
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

	private final GoodsService goodsService;

	/**
	 * pc端es分页查询商品信息
	 */
	@GetMapping("/page")
	public Result<EsPage<GoodsVo>> page(Long current, Long pageSize, Long categoryId, String name) {
		return Result.success(goodsService.pageGoods(current, pageSize, name, categoryId));
	}

}
