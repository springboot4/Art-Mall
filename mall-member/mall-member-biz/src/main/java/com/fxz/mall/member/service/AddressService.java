package com.fxz.mall.member.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.member.dto.AddressDTO;
import com.fxz.mall.member.entity.Address;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-14
 */
public interface AddressService extends IService<Address> {

	/**
	 * 添加
	 */
	Boolean addAddress(AddressDTO addressDTO);

	/**
	 * 修改
	 */
	Boolean updateAddress(AddressDTO addressDTO);

	/**
	 * 分页
	 */
	IPage<Address> pageAddress(Page<Address> pageParam, Address address);

	/**
	 * 获取单条
	 */
	Address findById(Long id);

	/**
	 * 获取当前会员全部地址
	 */
	List<AddressDTO> findAll();

	/**
	 * 删除
	 */
	Boolean deleteAddress(Long id);

}