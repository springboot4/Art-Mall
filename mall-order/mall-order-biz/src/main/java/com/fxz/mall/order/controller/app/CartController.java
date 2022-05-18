package com.fxz.mall.order.controller.app;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.order.dto.CartItemDTO;
import com.fxz.mall.order.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/17 17:21
 */
@RestController("appCartController")
@RequestMapping("/app/carts")
@RequiredArgsConstructor
public class CartController {

	private final CartServiceImpl cartService;

	/**
	 * 添加商品到购物车
	 * @param skuId 商品id
	 * @return 添加是否成功
	 */
	@PostMapping
	public Result<Boolean> addCartItem(@RequestParam Long skuId) {
		return Result.success(cartService.addCartItem(skuId));
	}

	/**
	 * 获取购物车
	 * @return 购物车
	 */
	@GetMapping
	public Result<List<CartItemDTO>> getCart() {
		Long memberId = SecurityUtil.getUser().getUserId();
		return Result.success(cartService.listCartItemByMemberId(memberId));
	}

	/**
	 * 更新购物车
	 */
	@PutMapping("/skuId/{skuId}")
	public <T> Result<T> updateCartItem(@PathVariable Long skuId, @RequestBody CartItemDTO cartItem) {
		cartItem.setSkuId(skuId);
		boolean result = cartService.updateCartItem(cartItem);
		return Result.judge(result);
	}

	/**
	 * 全选 OR 取消全选
	 */
	@PatchMapping("/check")
	public <T> Result<T> check(Boolean checked) {
		return Result.judge(cartService.checkAll(checked));
	}

	/**
	 * 删除购物车商品
	 */
	@DeleteMapping("/skuId/{skuId}")
	public <T> Result<T> removeCartItem(@PathVariable Long skuId) {
		return Result.judge(cartService.removeCartItem(skuId));
	}

	/**
	 * 清空购物车
	 */
	@DeleteMapping
	public Result<Boolean> deleteCart() {
		return Result.judge(cartService.deleteCart());
	}

}
