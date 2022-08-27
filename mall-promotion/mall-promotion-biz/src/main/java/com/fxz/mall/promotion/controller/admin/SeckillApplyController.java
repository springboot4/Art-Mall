package com.fxz.mall.promotion.controller.admin;

import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.mall.promotion.service.SeckillApplyService;
import com.fxz.mall.promotion.vo.SeckillApplyVO;
import com.fxz.mall.promotion.vo.SeckillTimelineVO;
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
	 * @param applyVOs 商家秒杀请求视图对象
	 */
	@PostMapping(path = "/{seckillId}")
	public Result<Void> addSeckillApply(@PathVariable Long seckillId, @RequestBody List<SeckillApplyVO> applyVOs) {
		seckillApplyService.addSeckillApply(seckillId, applyVOs);
		return Result.success();
	}

	/**
	 * 获取当天秒杀活动信息(时刻及对应时刻下的商品)
	 */
	@Ojbk
	@GetMapping("/list")
	public Result<List<SeckillTimelineVO>> listSeckillTime() {
		return Result.success(seckillApplyService.listSeckillTime());
	}

}