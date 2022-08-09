package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.SeckillVO;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.service.SeckillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController("appSeckillController")
@RequestMapping("/admin/seckill")
@RequiredArgsConstructor
public class SeckillController {

	private final SeckillService seckillService;

	/**
	 * 初始化秒杀活动
	 */
	@GetMapping("/initSeckill")
	public void initSeckill() {
		seckillService.initSeckill();
	}

	/**
	 * 更新秒杀请求
	 * @param seckillVO 秒杀活动视图对象
	 */
	@PutMapping("/updateSeckill")
	public Result<Seckill> updateSeckill(@RequestBody SeckillVO seckillVO) {
		return Result.judge(seckillService.updateSeckill(seckillVO));
	}

}