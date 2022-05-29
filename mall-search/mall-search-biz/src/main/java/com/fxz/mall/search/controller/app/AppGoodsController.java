package com.fxz.mall.search.controller.app;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.product.query.SpuPageQuery;
import com.fxz.mall.product.vo.GoodsDetailVO;
import com.fxz.mall.product.vo.GoodsPageVO;
import com.fxz.mall.search.entity.EsPage;
import com.fxz.mall.search.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/29 10:25
 */
@Ojbk
@RestController("appGoodsController")
@RequestMapping("/app/es/goods")
@RequiredArgsConstructor
public class AppGoodsController {

	private final GoodsService goodsService;

	/**
	 * app端es分页查询商品信息
	 */
	@GetMapping("/page")
	public Result<EsPage<GoodsPageVO>> listSpuPage(SpuPageQuery queryParams) {
		return Result.success(goodsService.appSpuPage(queryParams));
	}

	/**
	 * app端获取商品详情
	 */
	@GetMapping("/{spuId}")
	public Result<GoodsDetailVO> getSpuDetail(@PathVariable Long spuId) {
		return Result.success(goodsService.getAppSpuDetail(spuId));
	}

}
