package com.fxz.mall.member.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.member.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 20:46
 */
@FeignClient(value = FxzServerConstant.FXZ_MALL_USER, contextId = "remoteAddressService")
public interface RemoteAddressService {

	/**
	 * 获取当前会员全部地址
	 */
	@GetMapping(value = "/app/address/findAll")
	public Result<List<AddressDto>> findAll();

}
