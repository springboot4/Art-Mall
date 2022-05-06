package com.fxz.mall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.dto.SpuDto;
import com.fxz.mall.product.entity.Spu;
import com.fxz.mall.product.mapper.SpuMapper;
import com.fxz.mall.product.service.SpuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品表
 *
 * @author fxz
 * @date 2022-05-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

	private final SpuMapper spuMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addSpu(SpuDto spuDto) {
		Spu spu = new Spu();
		BeanUtils.copyProperties(spuDto, spu);
		spuMapper.insert(spu);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateSpu(SpuDto spuDto) {
		Spu spu = new Spu();
		BeanUtils.copyProperties(spuDto, spu);
		spuMapper.updateById(spu);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Spu> pageSpu(Page<Spu> pageParam, Spu spu) {
		return spuMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Spu findById(Long id) {
		return spuMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Spu> findAll() {
		return spuMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteSpu(Long id) {
		spuMapper.deleteById(id);
		return Boolean.TRUE;
	}

}