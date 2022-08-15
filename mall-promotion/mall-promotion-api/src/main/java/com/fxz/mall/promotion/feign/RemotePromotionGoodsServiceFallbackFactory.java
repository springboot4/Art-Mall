package com.fxz.mall.promotion.feign;

import com.fxz.common.mp.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/15 16:09
 */
@Slf4j
public class RemotePromotionGoodsServiceFallbackFactory implements FallbackFactory<RemotePromotionGoodsService> {

	@Override
	public RemotePromotionGoodsService create(Throwable cause) {
		return (form) -> {
			log.info("接口调用错误:{}", cause.getLocalizedMessage());
			return Result.failed();
		};
	}

}
