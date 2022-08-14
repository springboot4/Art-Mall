package com.fxz.mall.promotion.feign;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.entity.Seckill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 秒杀活动feign接口
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 13:51
 */
@FeignClient(contextId = "remoteSeckillService", value = "fxz-mall-promotion")
public interface RemoteSeckillService {

	/**
	 * 保存秒杀活动
	 * @param seckills 秒杀活动
	 */
	@PostMapping("/admin/seckill/save")
	Result<Seckill> saveSeckill(@RequestBody List<Seckill> seckills,
			@RequestHeader(SecurityConstants.FROM) String from);

}