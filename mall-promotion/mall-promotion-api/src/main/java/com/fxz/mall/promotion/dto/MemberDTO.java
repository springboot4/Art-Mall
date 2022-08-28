package com.fxz.mall.promotion.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/28 17:19
 */
@Data
public class MemberDTO implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 会员id
	 */
	private Long id;

	/**
	 * 会员昵称
	 */
	private String nickName;

}
