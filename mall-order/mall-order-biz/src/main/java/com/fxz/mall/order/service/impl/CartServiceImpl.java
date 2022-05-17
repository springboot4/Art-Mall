package com.fxz.mall.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.order.constant.OrderConstants;
import com.fxz.mall.order.dto.CartItemDTO;
import com.fxz.mall.order.service.CartService;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.feign.RemoteSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/17 17:23
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final RedisTemplate redisTemplate;

    private final RemoteSkuService remoteSkuService;

    /**
     * 添加商品到购物车
     *
     * @param skuId 商品id
     * @return 添加是否成功
     */
    @Override
    public Boolean addCartItem(Long skuId) {
        Long memberId = SecurityUtil.getUser().getUserId();

        BoundHashOperations cartHashOperations = getCartHashOperations(memberId);

        String hKey = String.valueOf(skuId);

        CartItemDTO cartItem;

        // 购物车已存在该商品，更新商品数量
        if (cartHashOperations.get(hKey) != null) {
            cartItem = (CartItemDTO) cartHashOperations.get(hKey);
            cartItem.setCount(cartItem.getCount() + 1);
            cartItem.setChecked(true);
            cartHashOperations.put(hKey, cartItem);
            return Boolean.TRUE;
        }

        // 购物车不存在该商品，添加商品至购物车
        cartItem = new CartItemDTO();
        CompletableFuture<Void> cartItemCompletableFuture = CompletableFuture.runAsync(() -> {
            SkuInfoDTO skuInfo = remoteSkuService.getSkuInfo(skuId).getData();
            if (skuInfo != null) {
                BeanUtil.copyProperties(skuInfo, cartItem);
                cartItem.setCount(1);
                cartItem.setStock(skuInfo.getStockNum());
                cartItem.setChecked(true);
            }
        });
        CompletableFuture.allOf(cartItemCompletableFuture).join();

        Assert.isTrue(cartItem.getSkuId() != null, "商品不存在");
        cartHashOperations.put(hKey, cartItem);
        return Boolean.TRUE;
    }

    /**
     * 获取购物车
     *
     * @return 购物车
     */
    @Override
    public List<CartItemDTO> listCartItemByMemberId(Long memberId) {
        return getCartHashOperations(memberId).values();
    }

    /**
     * 清空购物车
     */
    @Override
    public Boolean deleteCart() {
        return redisTemplate.delete(OrderConstants.CART_PREFIX + SecurityUtil.getUser().getUserId());
    }

    /**
     * 更新购物车
     */
    @Override
    public Boolean updateCartItem(CartItemDTO cartItem) {
        Long memberId = SecurityUtil.getUser().getUserId();

        BoundHashOperations cartHashOperations = getCartHashOperations(memberId);
        String hKey = String.valueOf(cartItem.getSkuId());

        if (cartHashOperations.get(hKey) != null) {
            CartItemDTO cacheCartItem = (CartItemDTO) cartHashOperations.get(hKey);

            if (cartItem.getChecked() != null) {
                cacheCartItem.setChecked(cartItem.getChecked());
            }
            if (cartItem.getCount() != null) {
                cacheCartItem.setCount(cartItem.getCount());
            }

            cartHashOperations.put(hKey, cacheCartItem);
        }
        return Boolean.TRUE;
    }

    /**
     * 删除购物车商品
     */
    @Override
    public Boolean removeCartItem(Long skuId) {
        Long memberId = SecurityUtil.getUser().getUserId();

        BoundHashOperations cartHashOperations = getCartHashOperations(memberId);

        String hKey = String.valueOf(skuId);

        cartHashOperations.delete(hKey);

        return Boolean.TRUE;
    }

    /**
     * 获取第一层，即某个用户的购物车
     */
    private BoundHashOperations getCartHashOperations(Long memberId) {
        String cartKey = OrderConstants.CART_PREFIX + memberId;
        return redisTemplate.boundHashOps(cartKey);
    }

}
