package com.fxz.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.product.entity.SpuAttributeValue;
import com.fxz.mall.product.mapper.SpuAttributeValueMapper;
import com.fxz.mall.product.service.SpuAttributeValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 商品属性项表
 *
 * @author fxz
 * @date 2022-05-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpuAttributeValueServiceImpl extends ServiceImpl<SpuAttributeValueMapper, SpuAttributeValue>
		implements SpuAttributeValueService {

	private final SpuAttributeValueMapper attributeValueMapper;

}