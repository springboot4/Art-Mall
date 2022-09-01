package com.fxz.mall.promotion.param;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fxz.mall.promotion.entity.Coupon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/29 20:22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CouponParam extends Coupon implements Serializable {

	private static final long serialVersionUID = -1L;

	private List<Long> ids;

	public LambdaQueryWrapper<Coupon> lambdaQuery() {
		return Wrappers.<Coupon>lambdaQuery().eq(Objects.nonNull(this.getId()), Coupon::getId, this.getId())
				.in(CollectionUtils.isNotEmpty(ids), Coupon::getId, ids)
				.like(StrUtil.isNotBlank(this.getCouponName()), Coupon::getCouponName, this.getCouponName())
				.eq(StrUtil.isNotBlank(this.getCouponType()), Coupon::getCouponType, this.getCouponType())
				.eq(StrUtil.isNotBlank(this.getGetType()), Coupon::getGetType, this.getGetType())
				.and(wr -> wr.isNotNull(Coupon::getStartTime).or().gt(Coupon::getEffectiveDays, 0));
	}

}
