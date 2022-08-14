package com.fxz.mall.promotion.feign;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 16:02
 */
@FeignClient(contextId = "remotePromotionGoodsService", value = "fxz-mall-promotion")
public interface RemotePromotionGoodsService {

	/**
	 * 清空数据库中过期的促销商品信息
	 */
	@DeleteMapping("/admin/promotionGoods/cleanInvalidPromotion")
	Result<Void> cleanInvalidPromotion(@RequestHeader(SecurityConstants.FROM) String from);

}
