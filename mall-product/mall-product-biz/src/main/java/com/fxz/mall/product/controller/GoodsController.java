package com.fxz.mall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.dto.GoodsDto;
import com.fxz.mall.product.service.impl.SpuServiceImpl;
import com.fxz.mall.product.vo.GoodsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理
 *
 * @author fxz
 * @date 2022-05-07
 */
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final SpuServiceImpl spuService;

    /**
     * 保存商品
     *
     * @param goodsDto 商品信息
     * @return 是否保存成功
     */
    @PostMapping("/add")
    public Result<Boolean> addGoods(@RequestBody GoodsDto goodsDto) {
        return Result.judge(spuService.addGoods(goodsDto));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    public Result<PageResult<GoodsVo>> page(Long current, Long pageSize, Long categoryId, String name) {
        return Result.success(PageResult.success(spuService.listGoods(new Page(current, pageSize), name, categoryId)));
    }

    /**
     * 删除商品信息
     */
    @DeleteMapping("/delete")
    public Result<Boolean> delete(Long id) {
        return Result.success(spuService.delete(id));
    }

}