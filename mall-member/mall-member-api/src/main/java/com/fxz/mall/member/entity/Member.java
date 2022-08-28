package com.fxz.mall.member.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.List;

/**
 * @author fxz
 * @date 2022-05-15
 */
@Data
@FieldNameConstants
@TableName("member")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 * 会员id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 生日
	 */
	private LocalDate birthday;

	/**
	 * 头像
	 */
	private String avatarUrl;

	/**
	 * openid
	 */
	private String openid;

	/**
	 * sessionKey
	 */
	private String sessionKey;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 会员积分
	 */
	private Integer point;

	/**
	 * 余额
	 */
	private Long balance;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 语言
	 */
	private String language;

	/**
	 * 删除标志
	 */
	@TableLogic
	private Integer deleted;

	/**
	 * 地址信息
	 */
	@TableField(exist = false)
	private List<Address> addressList;

}
