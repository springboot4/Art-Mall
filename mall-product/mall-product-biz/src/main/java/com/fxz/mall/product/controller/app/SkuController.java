package com.fxz.mall.product.controller.app;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.service.impl.SkuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [移动端]商品管理
 *
 * @author fxz
 * @date 2022-05-07
 */
@RestController("appSkuController")
@RequestMapping("/app/sku")
@RequiredArgsConstructor
public class SkuController {

	private final SkuServiceImpl skuService;

	/**
	 * 获取商品库存信息
	 */
	@GetMapping("/{skuId}/info")
	public Result<SkuInfoDTO> getSkuInfo(@PathVariable("skuId") Long skuId) {
		return Result.success(skuService.getSkuInfo(skuId));
	}

}