package com.fxz.mall.member.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.member.entity.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 20:46
 */
@FeignClient(value = FxzServerConstant.FXZ_MALL_MEMBER, contextId = "remoteMemberService")
public interface RemoteMemberService {

	/**
	 * 扣减会员余额
	 */
	@PutMapping("/member/current/balances/deduct")
	<T> Result<T> deductBalance(Long balances);

	/**
	 * 根据会员名查询用户信息
	 * @return 会员信息
	 */
	@GetMapping("/member/auth/loadUserByUsername/{username}")
	Result<Member> loadUserByUsername(@PathVariable("username") String username,
			@RequestHeader(SecurityConstants.FROM) String form);

	/**
	 * 根据会员id查询用户信息
	 * @param id 会员id
	 * @return 会员信息
	 */
	@GetMapping("/member/auth/loadUserByUserId/{id}")
	Result<Member> loadUserByUserId(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM) String form);

	/**
	 * 根据会员手机号查询用户信息
	 * @return 会员信息
	 */
	@GetMapping("/member/auth/loadUserByMobile/{mobile}")
	Result<Member> loadUserByMobile(@PathVariable("mobile") String mobile,
			@RequestHeader(SecurityConstants.FROM) String form);

	/**
	 * 根据会员openId查询用户信息
	 * @return 会员信息
	 */
	@GetMapping("/member/auth/loadUserByOpenId/{openId}")
	Result<Member> loadUserByOpenId(@PathVariable("openId") String openId,
			@RequestHeader(SecurityConstants.FROM) String form);

	/**
	 * 新增会员
	 * @param member 会员信息
	 */
	@PostMapping("/member/add")
	Result<Boolean> addMember(@RequestBody Member member, @RequestHeader(SecurityConstants.FROM) String form);

}
