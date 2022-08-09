package com.fxz.mall.promotion.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Data
@TableName("promotion_goods")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PromotionGoods extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 活动结束时间
	 */
	private LocalDateTime endTime;

	/**
	 * 货品名称
	 */
	private String goodsName;

	/**
	 * 限购数量
	 */
	private Integer limitNum;

	/**
	 * 卖出的商品数量
	 */
	private Integer num;

	/**
	 * 原价
	 */
	private Double originalPrice;

	/**
	 * 促销价格
	 */
	private Double price;

	/**
	 * 活动ID
	 */
	private Long promotionId;

	/**
	 * 促销库存
	 */
	private Integer quantity;

	/**
	 * 促销工具类型
	 */
	private String promotionType;

	/**
	 * 商品ID
	 */
	private Long goodsId;

	/**
	 * 货品SkuID
	 */
	private Long skuId;

	/**
	 * 活动开始时间
	 */
	private LocalDateTime startTime;

	/**
	 * 缩略图
	 */
	private String thumbnail;

	/**
	 * 活动标题
	 */
	private String title;

	/**
	 * 分类路径
	 */
	private String categoryPath;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Boolean deleteFlag;

}
