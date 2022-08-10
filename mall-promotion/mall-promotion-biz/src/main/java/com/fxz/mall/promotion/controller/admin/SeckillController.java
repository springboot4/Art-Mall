package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.vo.SeckillVO;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.service.SeckillService;
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

}