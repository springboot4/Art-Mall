package com.fxz.mall.support.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.fxz.mall.member.feign.RemoteMemberService;
import com.fxz.mall.support.extension.wechat.FxzWechatAuthenticationProvider;
import com.fxz.mall.support.service.member.FxzMemberUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/9/1 09:48
 */
@RequiredArgsConstructor
@AutoConfiguration
public class SecurityAutoConfigure {

	private final FxzMemberUserDetailsServiceImpl fxzMemberUserDetailsService;

	private final WxMaService wxMaService;

	private final RemoteMemberService remoteMemberService;

	/**
	 * 微信认证授权提供者
	 * @return 微信认证授权提供者
	 */
	@Bean
	public FxzWechatAuthenticationProvider wechatAuthenticationProvider() {
		FxzWechatAuthenticationProvider provider = new FxzWechatAuthenticationProvider();
		provider.setUserDetailsService(fxzMemberUserDetailsService);
		provider.setWxMaService(wxMaService);
		provider.setMemberFeignClient(remoteMemberService);
		return provider;
	}

}
