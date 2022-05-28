package com.fxz.mall.product.feign;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.constant.FxzServerConstant;
import com.fxz.mall.product.dto.GoodsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-05-28
 */
@FeignClient(contextId = "remoteGoodService", value = FxzServerConstant.FXZ_MALL_PRODUCT)
public interface RemoteGoodService {

	/**
	 * 根据spuId获取商品详情
	 */
	@GetMapping("/goods/{spuId}/detail")
	public Result<GoodsDto> getSpuDetail(@PathVariable("spuId") Long spuId,
			@RequestHeader(SecurityConstants.FROM) String from);

}
