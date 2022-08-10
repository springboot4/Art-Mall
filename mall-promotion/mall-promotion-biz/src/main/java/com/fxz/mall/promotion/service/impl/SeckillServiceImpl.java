package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.promotion.entity.*;
import com.fxz.mall.promotion.enums.SettingEnum;
import com.fxz.mall.promotion.mapper.SeckillMapper;
import com.fxz.mall.promotion.service.PromotionGoodsService;
import com.fxz.mall.promotion.service.SeckillApplyService;
import com.fxz.mall.promotion.service.SeckillService;
import com.fxz.mall.promotion.service.SettingService;
import io.seata.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeckillServiceImpl extends ServiceImpl<SeckillMapper, Seckill> implements SeckillService {

	/**
	 * 预创建活动数量
	 */
	private final Integer PRE_CREATION = 7;

	@Autowired
	private SeckillApplyService seckillApplyService;

	private final SettingService settingService;

	private final PromotionGoodsService promotionGoodsService;

	/**
	 * 初始化秒杀活动
	 */
	@Override
	public void initSeckill() {
		// 删除已有的秒杀活动
		this.remove(Wrappers.emptyWrapper());

		// 获取秒杀规则
		Setting setting = settingService.findSetting(SettingEnum.SECKILL_SETTING);
		SeckillSetting seckillSetting = JacksonUtil.parseObject(setting.getSettingValue(), SeckillSetting.class);

		// 保存秒杀活动
		for (int i = 0; i <= PRE_CREATION; i++) {
			Seckill seckill = new Seckill(i, seckillSetting.getHours(), seckillSetting.getSeckillRule());
			this.saveSeckill(seckill);
		}
	}

	/**
	 * 保存秒杀活动
	 */
	@Override
	public boolean saveSeckill(Seckill seckill) {
		// 检查秒杀活动时间是否合法
		LocalDateTime startTime = seckill.getStartTime();
		LocalDateTime endTime = seckill.getEndTime();
		LocalDateTime now = LocalDateTime.now();
		// if (now.isAfter(startTime)) {
		// throw new FxzException("活动起始时间不能小于当前时间");
		// }
		// if (now.isAfter(endTime)) {
		// throw new FxzException("活动结束时间不能小于当前时间");
		// }
		// if (startTime.isAfter(endTime)) {
		// throw new FxzException("活动起始时间必须大于结束时间");
		// }

		// 删除秒杀促销商品
		promotionGoodsService.remove(Wrappers.emptyWrapper());

		// todo 更新es中商品的促销信息

		// 保存秒杀活动
		return this.save(seckill);
	}

	/**
	 * 更新秒杀请求
	 * @param seckill 秒杀活动视图对象
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateSeckill(Seckill seckill) {
		// 秒杀活动重复校验
		checkStatus(seckill);
		// 活动时间合法性校验
		checkPromotionTime(seckill.getStartTime(), seckill.getEndTime());
		// 更新秒杀活动信息
		updateById(seckill);
		// 更新活动相关下关联的信息
		seckillApplyService.updateSeckillApplyTime(seckill);
		// 更新秒杀活动的商品数量
		return countSeckillGoodsNum(seckill.getId());
	}

	/**
	 * 关闭秒杀活动
	 * @param id 秒杀活动id
	 */
	@Override
	public void closeSeckill(Long id) {
		Seckill seckill = this.getById(id);
		if (Objects.isNull(seckill)) {
			throw new FxzException("秒杀活动不存在");
		}

		// 删除秒杀活动下的促销商品
		promotionGoodsService.remove(Wrappers.<PromotionGoods>lambdaQuery().eq(PromotionGoods::getPromotionId, id));

		// todo 更新es索引中商品的促销信息

		// 将当前秒杀活动起始时间更新为null
		this.update(Wrappers.<Seckill>lambdaUpdate().eq(Seckill::getId, id).set(Seckill::getStartTime, null)
				.set(Seckill::getEndTime, null));
	}

	/**
	 * 更新秒杀活动商品数量
	 * @param seckillId 秒杀活动id
	 * @return true or false
	 */
	@Override
	public boolean countSeckillGoodsNum(Long seckillId) {
		long count = seckillApplyService
				.count(Wrappers.<SeckillApply>lambdaQuery().eq(SeckillApply::getSeckillId, seckillId));
		return this.update(
				Wrappers.<Seckill>lambdaUpdate().set(Seckill::getGoodsNum, count).eq(Seckill::getId, seckillId));
	}

	/**
	 * 活动时间合法性校验
	 */
	private void checkPromotionTime(LocalDateTime startTime, LocalDateTime endTime) {
		if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
			throw new FxzException("活动时间不能为空");
		}

		LocalDateTime now = LocalDateTime.now();
		if (now.isAfter(startTime) || now.isAfter(endTime)) {
			throw new FxzException("活动时间不能早于当前时间");
		}

		if (startTime.isAfter(endTime)) {
			throw new FxzException("活动开始时间不能早于结束时间");
		}
	}

	/**
	 * 秒杀活动重复校验
	 */
	private void checkStatus(Seckill seckillVO) {
		// 检验当前促销活动是否存在
		Seckill seckill = this.getById(seckillVO.getId());
		if (Objects.isNull(seckill)) {
			throw new FxzException("当前促销活动不存在");
		}

		if (StringUtils.isNotBlank(seckill.getHours()) && Objects.nonNull(seckillVO.getStartTime())) {
			String[] split = seckill.getHours().split(StringPool.COMMA);
			Arrays.sort(split);

			// 设置秒杀活动的开始时间，以第一时间段为开始
			seckillVO.setStartTime(LocalDateTime.of(seckillVO.getStartTime().toLocalDate(),
					LocalTime.of(Integer.parseInt(split[0]), 0)));
			// 设置秒杀活动的结束时间，以这一天最后的时刻
			seckillVO.setEndTime(LocalDateTime.of(seckillVO.getStartTime().toLocalDate(), LocalTime.of(23, 59, 59)));

			// 判断当前时间段内是否有了秒杀活动
			long count = this.count(Wrappers.<Seckill>lambdaQuery().ne(Seckill::getId, seckillVO.getId())
					.ge(Seckill::getStartTime, seckillVO.getStartTime()).le(Seckill::getEndTime, seckill.getEndTime()));
			if (count > 0) {
				throw new FxzException("当前时间段内存在秒杀活动");
			}
		}

	}

}