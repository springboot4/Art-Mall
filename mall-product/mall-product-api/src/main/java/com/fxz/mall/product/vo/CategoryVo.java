package com.fxz.mall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类表
 *
 * @author fxz
 * @date 2022-05-04
 */
@Data
@Accessors(chain = true)
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 商品分类名称
     */
    private String name;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 图标地址
     */
    private String iconUrl;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 显示状态:( 0:隐藏 1:显示)
     */
    private Integer visible;
    /**
     * 子节点
     */
    private List<CategoryVo> children = new ArrayList<>();

}
