package com.fxz.mall.product.controller.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.query.SpuPageQuery;
import com.fxz.mall.product.service.impl.SpuServiceImpl;
import com.fxz.mall.product.vo.GoodsDetailVO;
import com.fxz.mall.product.vo.GoodsPageVO;
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
@RestController("appGoodsController")
@RequestMapping("/app/goods")
@RequiredArgsConstructor
public class GoodsController {

	private final SpuServiceImpl spuService;

	/**
	 * app端商品分页
	 */
	@GetMapping("/page")
	public Result<PageResult<GoodsPageVO>> listSpuPage(SpuPageQuery queryParams) {
		IPage<GoodsPageVO> result = spuService.listAppSpuPage(queryParams);
		return Result.success(PageResult.success(result));
	}

	/**
	 * todo es app端获取商品详情
	 */
	@GetMapping("/{spuId}")
	public Result<GoodsDetailVO> getSpuDetail(@PathVariable Long spuId) {
		GoodsDetailVO goodsDetailVO = spuService.getAppSpuDetail(spuId);
		return Result.success(goodsDetailVO);
	}

}