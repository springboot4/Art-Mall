package com.fxz.mall.order.controller.app;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.order.service.CartService;
import com.fxz.mall.order.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<Boolean> addCartItem(@RequestParam Long skuId){
        return Result.success(cartService.addCartItem(skuId));
    }

}
