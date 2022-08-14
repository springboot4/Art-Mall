package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.entity.Setting;
import com.fxz.mall.promotion.enums.SettingEnum;
import com.fxz.mall.promotion.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController("settingController")
@RequestMapping("/admin/setting")
@RequiredArgsConstructor
public class SettingController {

	private final SettingService settingService;

	@Ojbk
	@GetMapping("/findSetting")
	public Result<Setting> findSetting(SettingEnum seckillSetting) {
		return Result.success(settingService.findSetting(seckillSetting));
	}

}