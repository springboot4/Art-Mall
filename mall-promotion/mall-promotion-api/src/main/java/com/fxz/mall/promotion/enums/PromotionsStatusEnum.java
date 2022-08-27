package com.fxz.mall.promotion.enums;

import com.fxz.common.core.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 促销活动状态枚举
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/27 15:14
 */
@AllArgsConstructor
public enum PromotionsStatusEnum implements IBaseEnum<String> {

	/**
	 * 新建
	 */
	NEW("new", "新建"),

	/**
	 * 开始/上架
	 */
	START("start", "开始/上架"),

	/**
	 * 结束/下架
	 */
	END("end", "结束/下架"),

	/**
	 * 关闭/作废
	 */
	CLOSE("close", "紧急关闭/作废");

	@Getter
	private final String value;

	@Getter
	private final String label;

}
