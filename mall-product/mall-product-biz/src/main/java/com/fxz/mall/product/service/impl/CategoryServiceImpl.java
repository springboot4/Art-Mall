package com.fxz.mall.product.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.file.OssProperties;
import com.fxz.common.file.service.OssTemplate;
import com.fxz.mall.product.dto.CategoryDto;
import com.fxz.mall.product.entity.Category;
import com.fxz.mall.product.mapper.CategoryMapper;
import com.fxz.mall.product.service.CategoryService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

	private final CategoryMapper categoryMapper;

	private final OssTemplate ossTemplate;

	private final OssProperties ossProperties;

	/**
	 * 添加
	 */
	@Override
	public Boolean addCategory(CategoryDto categoryDto) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto, category);
		categoryMapper.insert(category);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateCategory(CategoryDto categoryDto) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto, category);
		categoryMapper.updateById(category);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Category> pageCategory(Page<Category> pageParam, Category category) {
		return categoryMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public Category findById(Long id) {
		return categoryMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Category> findAll() {
		return categoryMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteCategory(Long id) {
		categoryMapper.deleteById(id);
		return Boolean.TRUE;
	}

	/**
	 * 查询分类列表
	 * @return 商品分类列表
	 */
	@Override
	public List<Tree<Long>> listCategory(Long parentId) {
		List<TreeNode<Long>> treeNodes = categoryMapper
				.selectList(
						Wrappers.<Category>lambdaQuery().eq(Category::getVisible, 1L).orderByDesc(Category::getSort))
				.stream().map(getNodeFunction()).collect(Collectors.toList());
		return TreeUtil.build(treeNodes, Objects.nonNull(parentId) ? parentId : 0L);
	}

	/**
	 * 将分类对象构建为树节点对象
	 */
	@NotNull
	private Function<Category, TreeNode<Long>> getNodeFunction() {
		return category -> {
			TreeNode<Long> node = new TreeNode<>();
			node.setId(category.getId());
			node.setName(category.getName());
			node.setParentId(category.getParentId());
			node.setWeight(category.getSort());

			// 扩展属性
			Map<String, Object> extra = new HashMap<>();
			extra.put("iconUrl", category.getIconUrl());
			extra.put("visible", category.getVisible());
			extra.put("level", category.getLevel());
			Map<String, String> map = Maps.newHashMap();
			map.put("title", "custom");
			extra.put("scopedSlots", map);

			// if(Objects.equals(category.getLevel(),3)&&Objects.nonNull(category.getIconUrl())){
			// String[] split = category.getIconUrl().split("/");
			// if(CollectionUtils.isNotEmpty(Arrays.asList(split))){
			// String url = ossTemplate.getObjectURL(ossProperties.getBucketName(),
			// category.getIconUrl(), 1);
			// log.info(url);
			// extra.put("iconUrl",url);
			// }
			// }

			node.setExtra(extra);

			return node;
		};
	}

}