package com.fxz.mall.member.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.member.dto.AddressDto;
import com.fxz.mall.member.entity.Address;
import com.fxz.mall.member.service.impl.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fxz
 * @date 2022-05-14
 */
@RestController("appAddressController")
@RequestMapping("/app/address")
@RequiredArgsConstructor
public class AddressController {

	private final AddressServiceImpl addressService;

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody AddressDto addressDto) {
		return Result.success(addressService.addAddress(addressDto));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody AddressDto addressDto) {
		return Result.success(addressService.updateAddress(addressDto));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(addressService.deleteAddress(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<Address> findById(Long id) {
		return Result.success(addressService.findById(id));
	}

	/**
	 * 获取当前会员全部地址
	 */
	@GetMapping(value = "/findAll")
	public Result<List<AddressDto>> findAll() {
		return Result.success(addressService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Address>> pageAddress(Page<Address> pageParam, Address address) {
		return Result.success(PageResult.success(addressService.pageAddress(pageParam, address)));
	}

}