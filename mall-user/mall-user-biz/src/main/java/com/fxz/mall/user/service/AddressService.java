package com.fxz.mall.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.mall.user.dto.AddressDto;
import com.fxz.mall.user.entity.Address;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-14
 */
public interface AddressService extends IService<Address> {

	/**
	 * 添加
	 */
	Boolean addAddress(AddressDto addressDto);

	/**
	 * 修改
	 */
	Boolean updateAddress(AddressDto addressDto);

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
	List<AddressDto> findAll();

	/**
	 * 删除
	 */
	Boolean deleteAddress(Long id);

}