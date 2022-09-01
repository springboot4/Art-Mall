package com.fxz.mall.support.service.member;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.service.FxzUserDetailsService;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.member.entity.Member;
import com.fxz.mall.member.feign.RemoteMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 商城会员用户认证服务
 *
 * @author fxz
 */
@Service("fxzMemberUserDetailsServiceImpl")
@RequiredArgsConstructor
public class FxzMemberUserDetailsServiceImpl implements FxzUserDetailsService {

	private final RemoteMemberService memberFeignClient;

	/**
	 * 手机号码认证方式
	 * @param mobile 手机号
	 * @return UserDetails
	 */
	@Override
	public UserDetails loadUserByMobile(String mobile) {
		Member systemUser = memberFeignClient.loadUserByMobile(mobile, SecurityConstants.FROM_IN).getData();

		if (Objects.nonNull(systemUser)) {
			FxzAuthUser authUser = new FxzAuthUser(systemUser.getNickName(), systemUser.getMobile(), true, true, true,
					true, AuthorityUtils.commaSeparatedStringToAuthorityList(null));

			BeanUtils.copyProperties(systemUser, authUser);
			authUser.setUserId(systemUser.getId());

			return authUser;
		}

		throw new UsernameNotFoundException("用户不存在");
	}

	/**
	 * openid 认证方式
	 * @param openId 微信openId
	 * @return UserDetails
	 */
	public UserDetails loadUserByOpenId(String openId) {
		Member systemUser = memberFeignClient.loadUserByOpenId(openId, SecurityConstants.FROM_IN).getData();

		if (Objects.nonNull(systemUser)) {
			FxzAuthUser authUser = new FxzAuthUser(systemUser.getNickName(), systemUser.getOpenid(), true, true, true,
					true, AuthorityUtils.commaSeparatedStringToAuthorityList(null));
			BeanUtils.copyProperties(systemUser, authUser);
			authUser.setUserId(systemUser.getId());

			return authUser;
		}

		throw new UsernameNotFoundException("用户不存在");
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// 获取客户端id
		String clientId = SecurityUtil.getOAuth2ClientId();

		if (clientId.equals(SecurityConstants.APP_CLIENT_ID)) {
			// 移动端用户，认证方式是通过会员手机号
			return this.loadUserByMobile(username);
		}
		else if (clientId.equals(SecurityConstants.WEAPP_CLIENT_ID)) {
			// 小程序端用户，认证方式是通过微信三方标识 openid 认证
			return this.loadUserByOpenId(username);
		}
		return null;
	}

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	@Override
	public boolean support(String clientId, String grantType) {
		// 支持 移动端用户通过会员手机号、小程序端用户通过微信三方标识openid认证
		return SecurityConstants.APP_CLIENT_ID.equals(clientId) || SecurityConstants.WEAPP_CLIENT_ID.equals(clientId);
	}

}
