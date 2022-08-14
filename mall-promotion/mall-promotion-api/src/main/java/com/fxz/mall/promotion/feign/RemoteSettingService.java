package com.fxz.mall.promotion.feign;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.entity.Setting;
import com.fxz.mall.promotion.enums.SettingEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 促销设置feign接口
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 14:00
 */
@FeignClient(contextId = "remoteSettingService", value = "fxz-mall-promotion")
public interface RemoteSettingService {

	@GetMapping("/admin/setting/findSetting")
	Result<Setting> findSetting(@RequestParam("seckillSetting") SettingEnum seckillSetting);

}
