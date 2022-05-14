package com.fxz.mall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.dto.SkuDto;
import com.fxz.mall.product.dto.SkuInfoDTO;
import com.fxz.mall.product.entity.Sku;
import com.fxz.mall.product.mapper.SkuMapper;
import com.fxz.mall.product.service.SkuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品库存表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

	private final SkuMapper skuMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addSku(SkuDto skuDto) {
		Sku sku = new Sku();
		BeanUtils.copyProperties(skuDto, sku);
		skuMapper.insert(sku);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateSku(SkuDto skuDto) {
		Sku sku = new Sku();
		BeanUtils.copyProperties(skuDto, sku);
		skuMapper.updateById(sku);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Sku> pageSku(Page<Sku> pageParam, Sku sku) {
		return skuMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Sku findById(Long id) {
		return skuMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Sku> findAll() {
		return skuMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteSku(Long id) {
		skuMapper.deleteById(id);
		return Boolean.TRUE;
	}

	/**
	 * 获取商品库存信息
	 */
	@Override
	public SkuInfoDTO getSkuInfo(Long skuId) {
		return skuMapper.getSkuInfo(skuId);
	}

}