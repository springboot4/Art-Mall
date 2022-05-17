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

}
