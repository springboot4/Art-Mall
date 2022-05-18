package com.fxz.mall.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.user.dto.MemberDto;
import com.fxz.mall.user.entity.Member;
import com.fxz.mall.user.mapper.MemberMapper;
import com.fxz.mall.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

	private final MemberMapper memberMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addMember(MemberDto memberDto) {
		Member member = new Member();
		BeanUtils.copyProperties(memberDto, member);
		memberMapper.insert(member);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateMember(MemberDto memberDto) {
		Member member = new Member();
		BeanUtils.copyProperties(memberDto, member);
		memberMapper.updateById(member);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Member> pageMember(Page<Member> pageParam, Member member) {
		return memberMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Member findById(Long id) {
		return memberMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Member> findAll() {
		return memberMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteMember(Long id) {
		memberMapper.deleteById(id);
		return Boolean.TRUE;
	}

	/**
	 * 根据会员名查询用户信息
	 * @param username 会员名
	 * @return 会员信息
	 */
	@Override
	public Member loadUserByUsername(String username) {
		return this.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getNickName, username));
	}

	/**
	 * 分页查询会员信息
	 * @param nickName 会员昵称
	 */
	@Override
	public Page<Member> listMembersWithPage(Page<Member> page, String nickName) {
		page.setRecords(memberMapper.listMembersWithPage(page, nickName));
		return page;
	}

}