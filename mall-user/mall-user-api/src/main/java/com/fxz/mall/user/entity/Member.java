package com.fxz.mall.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author fxz
 * @date 2022-05-15
 */
@Data
@TableName("member")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 *
	 */
	private Long id;

	/**
	 *
	 */
	private Integer gender;

	/**
	 *
	 */
	private String nickName;

	private String password;

	/**
	 *
	 */
	private String mobile;

	/**
	 *
	 */
	private LocalDate birthday;

	/**
	 *
	 */
	private String avatarUrl;

	/**
	 *
	 */
	private String openid;

	/**
	 *
	 */
	private String sessionKey;

	/**
	 *
	 */
	private Integer status;

	/**
	 * 会员积分
	 */
	private Integer point;

	/**
	 *
	 */
	private Integer deleted;

	/**
	 *
	 */
	private Long balance;

	/**
	 *
	 */
	private String city;

	/**
	 *
	 */
	private String country;

	/**
	 *
	 */
	private String language;

	/**
	 *
	 */
	private String province;

}
