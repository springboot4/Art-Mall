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
@TableName("seckill_apply")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SeckillApply extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 删除标志
	 */
	private Boolean deleteFlag;

	/**
	 * 驳回原因
	 */
	private String failReason;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品原始价格
	 */
	private Long originalPrice;

	/**
	 * 价格
	 */
	private Long price;

	/**
	 * 促销活动申请状态
	 */
	private String promotionApplyStatus;

	/**
	 * 促销数量
	 */
	private Integer quantity;

	/**
	 * 已售数量
	 */
	private Integer salesNum;

	/**
	 * 活动ID
	 */
	private Long seckillId;

	/**
	 * 货品ID
	 */
	private Long skuId;

	/**
	 * 时刻
	 */
	private Integer timeLine;

}
