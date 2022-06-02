package com.fxz.mall.member.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.member.dto.MemberDto;
import com.fxz.mall.member.entity.Member;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-15
 */
public interface MemberService extends IService<Member> {

	/**
	 * 添加
	 */
	Boolean addMember(Member member);

	/**
	 * 修改
	 */
	Boolean updateMember(MemberDto memberDto);

	/**
	 * 分页
	 */
	IPage<Member> pageMember(Page<Member> pageParam, Member member);

	/**
	 * 获取单条
	 */
	Member findById(Long id);

	/**
	 * 获取全部
	 */
	List<Member> findAll();

	/**
	 * 删除
	 */
	Boolean deleteMember(Long id);

	/**
	 * 根据会员名查询用户信息
	 * @param username 会员名
	 * @return 会员信息
	 */
	Member loadUserByUsername(String username);

	/**
	 * 分页查询会员信息
	 * @param nickName 会员昵称
	 */
	Page<Member> listMembersWithPage(Page<Member> page, String nickName);

	/**
	 * 根据手机号查询会员信息
	 * @param mobile 手机号
	 * @return 会员信息
	 */
	Member loadUserByMobile(String mobile);

	/**
	 * 根据会员openId查询用户信息
	 * @return 会员信息
	 */
	Member loadUserByOpenId(String openId);

	/**
	 * 根据会员id查询用户信息
	 * @param id 会员id
	 * @return 会员信息
	 */
	Member loadUserByUserId(Long id);

}