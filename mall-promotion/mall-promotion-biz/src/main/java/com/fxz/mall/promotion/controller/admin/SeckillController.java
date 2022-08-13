package com.fxz.mall.promotion.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.dto.SeckillDTO;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.service.SeckillService;
import com.fxz.mall.promotion.vo.SeckillVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController("seckillController")
@RequestMapping("/admin/seckill")
@RequiredArgsConstructor
public class SeckillController {

	private final SeckillService seckillService;

	/**
	 * 初始化秒杀活动
	 */
	@GetMapping("/init")
	public void initSeckill() {
		seckillService.initSeckill();
	}

	/**
	 * 更新秒杀活动
	 * @param seckillVO 秒杀活动视图对象
	 */
	@PutMapping("/update")
	public Result<Seckill> updateSeckill(@RequestBody SeckillVO seckillVO) {
		return Result.judge(seckillService.updateSeckill(seckillVO));
	}

	/**
	 * 关闭秒杀活动
	 * @param id 秒杀活动id
	 */
	@PutMapping("/close/{id}")
	public Result<Void> closeSeckill(@PathVariable("id") Long id) {
		seckillService.closeSeckill(id);
		return Result.success();
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Seckill>> pageSeckill(Page pageParam) {
		return Result.success(PageResult.success(seckillService.pageSeckill(pageParam)));
	}

	/**
	 * 根据秒杀活动Id获取秒杀活动以及秒杀活动下的商家秒杀请求
	 * @param id 秒杀活动id
	 */
	@GetMapping(value = "/{id}")
	public Result<SeckillDTO> getSeckillAndApplyById(@PathVariable("id") Long id) {
		return Result.success(seckillService.getSeckillAndApplyById(id));
	}

}