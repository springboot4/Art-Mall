package com.fxz.mall.product.feign;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.constant.FxzServerConstant;
import com.fxz.mall.product.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-05-29
 */
@FeignClient(contextId = "remoteCategoryService", value = FxzServerConstant.FXZ_MALL_PRODUCT)
public interface RemoteCategoryService {

	@GetMapping(value = "/category/findById")
	Result<Category> findById(@RequestParam("id") Long id, @RequestHeader(SecurityConstants.FROM) String from);

}
