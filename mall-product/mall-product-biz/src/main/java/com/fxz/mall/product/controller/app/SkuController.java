package com.fxz.mall.product.controller.app;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.product.dto.CheckPriceDTO;
import com.fxz.mall.product.dto.LockStockDTO;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.service.impl.SkuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
	 * 锁定库存
	 * @return 锁定是否成功
	 */
	@PutMapping("/lock")
	public Result<Boolean> lockStock(@RequestBody LockStockDTO lockStockDTO) {
		return Result.success(skuService.lockStock(lockStockDTO));
	}

	/**
	 * 释放库存
	 * @param orderToken 订单号
	 * @return 是否释放成功
	 */
	@Ojbk(inner = true)
	@PutMapping("/unlock")
	public Result<Boolean> unlockStock(String orderToken) {
		return Result.judge(skuService.unlockStock(orderToken));
	}

	@PutMapping("/deduct")
	public Result<Boolean> deductStock(String orderToken) {
		return Result.judge(skuService.deductStock(orderToken));
	}

	/**
	 * 获取商品库存信息
	 */
	@GetMapping("/{skuId}/info")
	public Result<SkuInfoDTO> getSkuInfo(@PathVariable("skuId") Long skuId) {
		return Result.success(skuService.getSkuInfo(skuId));
	}

	/**
	 * 商品验价
	 * @param checkPriceDTO 校验价格dto
	 * @return 价格是否相同
	 */
	@PostMapping("/price/check")
	public Result<Boolean> checkPrice(@RequestBody CheckPriceDTO checkPriceDTO) {
		return Result.success(skuService.checkPrice(checkPriceDTO));
	}

}