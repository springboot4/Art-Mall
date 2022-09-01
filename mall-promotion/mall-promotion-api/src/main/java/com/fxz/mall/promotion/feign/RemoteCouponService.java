package com.fxz.mall.promotion.feign;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.param.CouponParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 优惠券feign接口
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 13:51
 */
@FeignClient(contextId = "remoteCouponService", value = "fxz-mall-promotion")
public interface RemoteCouponService {

	/**
	 * 查询优惠券信息
	 * @param couponParam 查询参数
	 */
	@PostMapping(value = "/app/coupon/list")
	Result<List<Map<String, Object>>> couponList(@RequestBody CouponParam couponParam);

}