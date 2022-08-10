package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/9 16:07
 */
@Getter
@AllArgsConstructor
public enum SettingEnum implements IBaseEnum<String> {

	/**
	 * 秒杀活动设置
	 */
	SECKILL_SETTING("seckill_setting", "秒杀规则设置");

	private final String value;

	private final String label;

}
