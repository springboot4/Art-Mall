package com.fxz.mall.promotion.feign;

import com.fxz.common.mp.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * 根据id查询优惠券信息
	 * @param ids 优惠券id集合
	 */
	@GetMapping(value = "/app/coupon/list")
	Result<List<Map<String, Object>>> couponList(@RequestParam("ids") List<Long> ids);

}