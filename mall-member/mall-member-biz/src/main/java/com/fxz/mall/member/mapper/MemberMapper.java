package com.fxz.mall.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.mall.member.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-15
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

	/**
	 * 分页查询会员信息 <a href="https://blog.csdn.net/qq_44447372/article/details/122598462"/>
	 * <a href="http://www.mybatis.cn/archives/1010.html"/>
	 * @param nickname 会员昵称
	 */
	@Select("<script>" + "select * from member" + "<if test='nickname != null and nickname.trim() neq \"\"'>"
			+ "       WHERE nick_name like concat('%',#{nickname},'%')" + " </if>"
			+ " ORDER BY update_time DESC, create_time DESC" + "</script>")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(property = "addressList", column = "id",
			many = @Many(select = "com.fxz.mall.user.mapper.AddressMapper.listByUserId")) })
	List<Member> listMembersWithPage(Page<Member> page, @Param("nickname") String nickname);

}