package com.fxz.mall.order.controller.app;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.order.service.impl.OrderServiceImpl;
import com.fxz.mall.order.vo.OrderConfirmVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 17:44
 */
@RestController("appOrderController")
@RequestMapping("/app/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderServiceImpl orderService;

	/**
	 * 订单确认 → 进入创建订单页面
	 * <p>
	 * 获取购买商品明细、用户默认收货地址、防重提交唯一token 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
	 * @param skuId 直接购买必填，购物车结算不填
	 * @return OrderConfirmVO
	 */
	@Ojbk
	@PostMapping("/confirm")
	public Result<OrderConfirmVO> confirm(@RequestParam(required = false) Long skuId) {
		OrderConfirmVO result = orderService.confirm(skuId);
		return Result.success(result);
	}

}
