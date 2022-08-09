package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.service.SeckillApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController("appSeckillApplyController")
@RequestMapping("/admin/seckillApply")
@RequiredArgsConstructor
public class SeckillApplyController {

	private final SeckillApplyService seckillApplyService;

	/**
	 * 删除秒杀活动申请
	 */
	@DeleteMapping("/{seckillId}/{id}")
	public Result<String> deleteSeckillApply(@PathVariable String seckillId, @PathVariable String id) {
		seckillApplyService.removeSeckillApply(seckillId, id);
		return Result.success();
	}

}