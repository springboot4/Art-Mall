package com.fxz.mall.product.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.product.dto.GoodsDTO;
import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.service.SkuService;
import com.fxz.mall.product.service.impl.SkuServiceImpl;
import com.fxz.mall.product.service.impl.SpuServiceImpl;
import com.fxz.mall.product.vo.GoodsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	private final SkuService skuService;

	private final SpuServiceImpl spuService;

	/**
	 * 保存商品
	 * @param goodsDTO 商品信息
	 * @return 是否保存成功
	 */
	@PostMapping("/add")
	public Result<Boolean> addGoods(@RequestBody GoodsDTO goodsDTO) {
		return Result.judge(spuService.addGoods(goodsDTO));
	}

	/**
	 * pc端分页
	 */
	@GetMapping("/page")
	public Result<PageResult<GoodsVO>> page(Long current, Long pageSize, Long categoryId, String name) {
		return Result.success(PageResult.success(spuService.listGoods(new Page(current, pageSize), name, categoryId)));
	}

	/**
	 * 删除商品信息
	 */
	@DeleteMapping("/delete")
	public Result<Boolean> delete(Long id) {
		return Result.success(spuService.delete(id));
	}

	/**
	 * 根据spuId获取商品详情
	 */
	@Ojbk(inner = true)
	@GetMapping("/{spuId}/detail")
	public Result<GoodsDTO> getSpuDetail(@PathVariable("spuId") Long spuId) {
		return Result.success(spuService.getSpuDetail(spuId));
	}

	/**
	 * 获取sku列表
	 */
	@GetMapping("/listSku")
	public Result<List<Sku>> listSku() {
		return Result.success(skuService.list());
	}

}