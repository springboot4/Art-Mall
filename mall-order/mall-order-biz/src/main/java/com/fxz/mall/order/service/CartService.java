package com.fxz.mall.order.service;

import com.fxz.mall.order.dto.CartItemDTO;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/17 17:23
 */
public interface CartService {
    /**
     * 添加商品到购物车
     *
     * @param skuId 商品id
     * @return 添加是否成功
     */
    Boolean addCartItem(Long skuId);

    /**
     * 获取购物车
     *
     * @return 购物车
     */
    List<CartItemDTO> listCartItemByMemberId(Long memberId);

    /**
     * 清空购物车
     */
    Boolean deleteCart();

    /**
     * 更新购物车
     */
    Boolean updateCartItem(CartItemDTO cartItem);

    /**
     * 删除购物车商品
     */
    Boolean removeCartItem(Long skuId);

}
