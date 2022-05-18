package com.fxz.mall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.mall.user.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-14
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

	@Select("<script>" + " SELECT * from address where member_id =#{userId} " + "</script>")
	List<Address> listByUserId(Long userId);

}