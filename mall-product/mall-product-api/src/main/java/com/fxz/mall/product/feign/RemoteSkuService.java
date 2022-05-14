package com.fxz.mall.product.feign;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.constant.FxzServerConstant;
import com.fxz.mall.product.dto.SkuInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-05-14 20:38
 */
@FeignClient(contextId = "remoteSkuService", value = FxzServerConstant.FXZ_MALL_PRODUCT)
public interface RemoteSkuService {

	@GetMapping("/app/sku/{skuId}/info")
	public Result<SkuInfoDTO> getSkuInfo(@PathVariable("skuId") Long skuId);

}
