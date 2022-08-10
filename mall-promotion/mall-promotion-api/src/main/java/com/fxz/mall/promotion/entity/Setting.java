package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Data
@TableName("setting")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Setting extends BaseEntity {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 配置值value
	 */
	private String settingValue;

	/**
	 * 删除标志
	 */
	private Boolean deleteFlag;

}
