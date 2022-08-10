package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.mall.promotion.service.SeckillApplyService;
import com.fxz.mall.promotion.vo.SeckillApplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fxz
 * @date 2022-08-09
 */
@RestController("seckillApplyController")
@RequestMapping("/admin/seckillApply")
@RequiredArgsConstructor
public class SeckillApplyController {

	private final SeckillApplyService seckillApplyService;

	/**
	 * 删除秒杀活动申请
	 */
	@DeleteMapping("/{seckillId}/{id}")
	public Result<String> deleteSeckillApply(@PathVariable("seckillId") Long seckillId, @PathVariable("id") Long id) {
		seckillApplyService.removeSeckillApply(seckillId, id);
		return Result.success();
	}

	/**
	 * 添加秒杀活动申请
	 * @param seckillId 秒杀活动id
	 * @param applyVos 商家秒杀请求视图对象
	 */
	@PostMapping(path = "/{seckillId}")
	public Result<Void> addSeckillApply(@PathVariable Long seckillId, @RequestBody List<SeckillApplyVO> applyVos) {
		seckillApplyService.addSeckillApply(seckillId, applyVos);
		return Result.success();
	}

}