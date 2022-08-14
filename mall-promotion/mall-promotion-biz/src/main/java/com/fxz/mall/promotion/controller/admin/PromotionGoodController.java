package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/admin/promotionGoods")
@RequiredArgsConstructor
public class PromotionGoodController {

	private final PromotionGoodsService promotionGoodsService;

	/**
	 * 清除过期促销商品
	 */
	@Ojbk(inner = true)
	@DeleteMapping("/cleanInvalidPromotion")
	public void cleanInvalidPromotion() {
		promotionGoodsService.cleanInvalidPromotion();
	}

}