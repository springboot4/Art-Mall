package com.fxz.mall.user.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.user.entity.Member;
import com.fxz.mall.user.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/15 19:53
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberServiceImpl memberService;

	/**
	 * 扣减会员余额
	 */
	@PutMapping("/current/balances/deduct")
	public <T> Result<T> deductBalance(Long balances) {
		Long memberId = SecurityUtil.getUser().getUserId();
		boolean result = memberService.update(new LambdaUpdateWrapper<Member>()
				.setSql("balance = balance - " + balances).eq(Member::getId, memberId));
		return Result.judge(result);
	}

	/**
	 * 根据会员名查询用户信息
	 * @param username 会员名
	 * @return 会员信息
	 */
	@Ojbk
	@GetMapping("/auth/loadUserByUsername/{username}")
	public Result<Member> loadUserByUsername(@PathVariable("username") String username) {
		return Result.success(memberService.loadUserByUsername(username));
	}

}
