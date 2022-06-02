package com.fxz.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author fxz
 * @date 2022-05-14
 */
@Data
@TableName("address")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 *
	 */
	private Long id;

	/**
	 * 会员ID
	 */
	private Long memberId;

	/**
	 * 收货人姓名
	 */
	private String consigneeName;

	/**
	 * 收货人联系方式
	 */
	private String consigneeMobile;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 市
	 */
	private String city;

	/**
	 * 区
	 */
	private String area;

	/**
	 * 详细地址
	 */
	private String detailAddress;

	/**
	 * 邮编
	 */
	private String zipCode;

	/**
	 * 是否默认地址
	 */
	private Integer defaulted;

}
