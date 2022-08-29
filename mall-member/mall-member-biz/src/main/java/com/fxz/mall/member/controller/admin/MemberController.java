package com.fxz.mall.member.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.mp.result.ResultCode;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.member.entity.Member;
import com.fxz.mall.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	 * 分页查询会员信息
	 * @param nickName 会员昵称
	 */
	@GetMapping("/page")
	public Result<PageResult<Member>> listMembersWithPage(Page<Member> page, String nickName) {
		return Result.success(PageResult.success(memberService.listMembersWithPage(page, nickName)));
	}

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
	@Ojbk(inner = true)
	@GetMapping("/auth/loadUserByUsername/{username}")
	public Result<Member> loadUserByUsername(@PathVariable("username") String username) {
		return Result.success(memberService.loadUserByUsername(username));
	}

	/**
	 * 根据会员id查询用户信息
	 * @param id 会员id
	 * @return 会员信息
	 */
	@Ojbk(inner = true)
	@GetMapping("/auth/loadUserByUserId/{id}")
	public Result<Member> loadUserByUserId(@PathVariable("id") Long id) {
		return Result.success(memberService.loadUserByUserId(id));
	}

	/**
	 * 根据手机号查询会员信息
	 * @param mobile 手机号
	 * @return 会员信息
	 */
	@Ojbk(inner = true)
	@GetMapping("/auth/loadUserByMobile/{mobile}")
	public Result<Member> loadUserByMobile(@PathVariable("mobile") String mobile) {
		return Result.success(memberService.loadUserByMobile(mobile));
	}

	/**
	 * 根据会员openId查询用户信息
	 * @return 会员信息
	 */
	@Ojbk(inner = true)
	@GetMapping("/auth/loadUserByOpenId/{openId}")
	public Result<Member> loadUserByOpenId(@PathVariable("openId") String openId) {
		Member member = memberService.loadUserByOpenId(openId);

		if (member == null) {
			return Result.failed(ResultCode.USER_NOT_EXIST);
		}

		return Result.success(member);
	}

	/**
	 * 新增会员
	 * @param member 会员信息
	 */
	@Ojbk(inner = true)
	@PostMapping("/add")
	public Result<Boolean> addMember(@RequestBody Member member) {
		return Result.success(memberService.addMember(member));
	}

	/**
	 * 根据会员id获取会员指定列信息
	 * @param columns 列信息
	 * @param ids 会员id集合
	 */
	@Ojbk
	@GetMapping("/listMemberMap")
	public Result<List<Map<String, Object>>> listMemberMap(@RequestParam(value = "columns") String columns,
			@RequestParam(value = "ids", required = false) List<Long> ids) {
		return Result.success(memberService.listMemberMap(columns, ids));
	}

	/**
	 * 列出所有会员信息
	 */
	@GetMapping("/listMembers")
	public Result<List<Member>> listMembers() {
		return Result.success(memberService.list());
	}

}
