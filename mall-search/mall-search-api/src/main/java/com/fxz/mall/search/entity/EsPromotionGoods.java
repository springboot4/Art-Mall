package com.fxz.mall.search.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fxz.common.canal.annotation.CanalModel;
import com.fxz.common.canal.common.FieldNamingPolicy;
import com.fxz.common.core.serializer.ImgUrl;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 商品
 *
 * @author fxz
 * @date 2022-05-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@CanalModel(database = "fxz_mall_promotion", table = "promotion_goods",
		fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
public class EsPromotionGoods extends BaseEntity {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 活动开始时间
	 */
	private LocalDateTime startTime;

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
	private Long originalPrice;

	/**
	 * 促销价格
	 */
	private Long price;

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
	private Integer deleteFlag;

}
