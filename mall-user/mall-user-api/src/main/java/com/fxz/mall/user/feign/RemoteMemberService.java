package com.fxz.mall.user.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.user.entity.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 20:46
 */
@FeignClient(value = FxzServerConstant.FXZ_MALL_USER, contextId = "remoteMemberService")
public interface RemoteMemberService {

	/**
	 * 扣减会员余额
	 */
	@PutMapping("/member/current/balances/deduct")
	public <T> Result<T> deductBalance(Long balances);

	/**
	 * 根据会员名查询用户信息
	 * @return 会员信息
	 */
	@GetMapping("/member/auth/loadUserByUsername/{username}")
	public Result<Member> loadUserByUsername(@PathVariable("username") String username,
			@RequestHeader(SecurityConstants.FROM) String form);

	/**
	 * 根据会员手机号查询用户信息
	 * @return 会员信息
	 */
	@GetMapping("/member/auth/loadUserByMobile/{mobile}")
	public Result<Member> loadUserByMobile(@PathVariable("mobile") String mobile,
			@RequestHeader(SecurityConstants.FROM) String form);

}
