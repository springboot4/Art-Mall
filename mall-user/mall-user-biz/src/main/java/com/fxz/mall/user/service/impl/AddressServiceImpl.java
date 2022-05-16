package com.fxz.mall.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.constant.FxzConstant;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.user.dto.AddressDto;
import com.fxz.mall.user.entity.Address;
import com.fxz.mall.user.mapper.AddressMapper;
import com.fxz.mall.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author fxz
 * @date 2022-05-14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

	private final AddressMapper addressMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addAddress(AddressDto addressDto) {
		Long memberId = SecurityUtil.getUser().getUserId();

		Address address = new Address();
		BeanUtil.copyProperties(addressDto, address);
		address.setMemberId(memberId);

		boolean result = this.save(address);

		if (result) {
			// 修改其他默认地址为非默认
			if (FxzConstant.STATUS_YES.equals(addressDto.getDefaulted())) {
				this.update(new LambdaUpdateWrapper<Address>().eq(Address::getMemberId, memberId)
						.eq(Address::getDefaulted, FxzConstant.STATUS_YES).ne(Address::getId, address.getId())
						.set(Address::getDefaulted, 0));
			}
		}

		return result;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateAddress(AddressDto addressDto) {
		Long memberId = SecurityUtil.getUser().getUserId();

		Address address = new Address();
		BeanUtil.copyProperties(addressDto, address);

		boolean result = this.updateById(address);

		if (result) {
			// 修改其他默认地址为非默认
			if (FxzConstant.STATUS_YES.equals(addressDto.getDefaulted())) {
				this.update(new LambdaUpdateWrapper<Address>().eq(Address::getMemberId, memberId)
						.eq(Address::getDefaulted, FxzConstant.STATUS_YES).ne(Address::getId, address.getId())
						.set(Address::getDefaulted, 0));
			}
		}
		return result;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Address> pageAddress(Page<Address> pageParam, Address address) {
		return addressMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Address findById(Long id) {
		return addressMapper.selectById(id);
	}

	/**
	 * 获取当前会员全部地址
	 */
	@Override
	public List<AddressDto> findAll() {
		FxzAuthUser fxzAuthUser = SecurityUtil.getUser();
		Long userId = fxzAuthUser.getUserId();

		List<Address> umsAddressList = this.list(
				new LambdaQueryWrapper<Address>().eq(Address::getMemberId, userId).orderByDesc(Address::getDefaulted));

		return Optional.ofNullable(umsAddressList).orElse(new ArrayList<>()).stream().map(address -> {
			AddressDto memberAddressDTO = new AddressDto();
			BeanUtil.copyProperties(address, memberAddressDTO);
			return memberAddressDTO;
		}).collect(Collectors.toList());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteAddress(Long id) {
		addressMapper.deleteById(id);
		return Boolean.TRUE;
	}

}