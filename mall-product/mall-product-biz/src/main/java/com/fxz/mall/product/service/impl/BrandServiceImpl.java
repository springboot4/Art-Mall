package com.fxz.mall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.dto.BrandDto;
import com.fxz.mall.product.entity.Brand;
import com.fxz.mall.product.mapper.BrandMapper;
import com.fxz.mall.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌表
 *
 * @author fxz
 * @date 2022-05-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    private final BrandMapper brandMapper;

    /**
     * 添加
     */
    @Override
    public Boolean addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);
        brandMapper.insert(brand);
        return Boolean.TRUE;
    }

    /**
     * 修改
     */
    @Override
    public Boolean updateBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);
        brandMapper.updateById(brand);
        return Boolean.TRUE;
    }

    /**
     * 分页
     */
    @Override
    public IPage<Brand> pageBrand(Page<Brand> pageParam, Brand brand) {
        return brandMapper.selectPage(pageParam, Wrappers.<Brand>lambdaQuery()
                .like(StringUtils.isNotBlank(brand.getName()), Brand::getName, brand.getName())
                .orderByAsc(Brand::getSort));
    }

    /**
     * 获取单条
     */
    @Override
    public Brand findById(Long id) {
        return brandMapper.selectById(id);
    }

    /**
     * 获取全部
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 删除
     */
    @Override
    public Boolean deleteBrand(Long id) {
        brandMapper.deleteById(id);
        return Boolean.TRUE;
    }

}