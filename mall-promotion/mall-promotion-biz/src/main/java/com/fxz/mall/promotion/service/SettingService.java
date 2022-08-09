package com.fxz.mall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.promotion.entity.Setting;
import com.fxz.mall.promotion.enums.SettingEnum;

/**
 * @author fxz
 * @date 2022-08-09
 */
public interface SettingService extends IService<Setting> {

	/**
	 * 获取设置规则
	 */
	Setting findSetting(SettingEnum seckillSetting);

}