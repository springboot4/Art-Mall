package com.fxz.mall.product.controller;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.dto.GoodsDto;
import com.fxz.mall.product.service.impl.SpuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 *
 * @author fxz
 * @date 2022-05-07
 */
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

	private final SpuServiceImpl spuService;

	/**
	 * 保存商品
	 * @param goodsDto 商品信息
	 * @return 是否保存成功
	 */
	@PostMapping("/add")
	public Result<Boolean> addGoods(@RequestBody GoodsDto goodsDto) {
		return Result.judge(spuService.addGoods(goodsDto));
	}

}