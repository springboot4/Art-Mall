package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.entity.Setting;
import com.fxz.mall.promotion.enums.SettingEnum;
import com.fxz.mall.promotion.mapper.SettingMapper;
import com.fxz.mall.promotion.service.SettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {

	/**
	 * 获取设置的规则
	 */
	@Cacheable(value = "setting", key = "#seckillSetting.value", unless = "#result==null")
	@Override
	public Setting findSetting(SettingEnum seckillSetting) {
		if (Objects.isNull(seckillSetting)) {
			return null;
		}

		return this.getOne(Wrappers.<Setting>lambdaQuery().eq(Setting::getId, seckillSetting.getValue()));
	}

}