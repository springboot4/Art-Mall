package com.fxz.mall.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.user.dto.MemberDto;
import com.fxz.mall.user.entity.Member;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-15
 */
public interface MemberService extends IService<Member> {

	/**
	 * 添加
	 */
	Boolean addMember(MemberDto memberDto);

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

}