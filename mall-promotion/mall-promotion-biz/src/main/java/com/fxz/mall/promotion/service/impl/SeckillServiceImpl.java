package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.promotion.dto.SeckillDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

		ArrayList<Seckill> seckills = new ArrayList<>();
		// 保存秒杀活动
		for (int i = 0; i <= PRE_CREATION; i++) {
			Seckill seckill = new Seckill(i, seckillSetting.getHours(), seckillSetting.getSeckillRule());
			// 检验生成的秒杀活动时间是否合法
			this.checkPromotionTime(seckill.getStartTime(), seckill.getEndTime());
			// 删除掉所有的促销商品
			promotionGoodsService.remove(Wrappers.emptyWrapper());
			seckills.add(seckill);
		}
		// 保存秒杀活动
		this.saveBatch(seckills);
	}

	/**
	 * 保存秒杀活动
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveSeckill(List<Seckill> seckills) {
		log.info("保存秒杀活动:{}", seckills);
		seckills.forEach(seckill -> {
			// 检查秒杀活动时间是否合法
			this.checkPromotionTime(seckill.getStartTime(), seckill.getEndTime());
			// 如果当天存在秒杀活动，不保存
			LocalDateTime dateTime = LocalDateTime.of(seckill.getStartTime().toLocalDate(), LocalTime.of(0, 0, 0));
			if (Objects.nonNull(
					this.getOne(Wrappers.<Seckill>lambdaQuery().ge(Seckill::getStartTime, dateTime).last("limit 1")))) {
				throw new FxzException(String.format("当前时间点存在秒杀活动 %s,保存秒杀活动失败！", seckill.getStartTime()));
			}
		});

		// 保存秒杀活动
		return this.saveBatch(seckills);
	}

	/**
	 * 更新秒杀请求
	 * @param seckill 秒杀活动视图对象
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateSeckill(Seckill seckill) {
		// 秒杀活动重复校验
		this.checkStatus(seckill);
		// 活动时间合法性校验
		this.checkPromotionTime(seckill.getStartTime(), seckill.getEndTime());
		// 更新秒杀活动信息
		this.updateById(seckill);
		// 更新活动相关下关联的信息
		seckillApplyService.updateSeckillApplyTime(seckill);
		// 更新秒杀活动的商品数量
		return this.countSeckillGoodsNum(seckill.getId());
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
	 * 分页查询秒杀活动
	 */
	@Override
	public IPage<Seckill> pageSeckill(Page pageParam) {
		return this.page(pageParam, Wrappers.<Seckill>lambdaQuery().orderByDesc(Seckill::getApplyEndTime));
	}

	/**
	 * 根据秒杀活动Id获取秒杀活动以及秒杀活动下的商家秒杀请求
	 * @param seckillId 秒杀活动id
	 */
	@Override
	public SeckillDTO getSeckillAndApplyById(Long seckillId) {
		return this.baseMapper.getSeckillAndApplyById(seckillId);
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
			Seckill obj = this.getOne(Wrappers.<Seckill>lambdaQuery().ne(Seckill::getId, seckillVO.getId())
					.ge(Seckill::getStartTime, seckillVO.getStartTime()).le(Seckill::getEndTime, seckill.getEndTime())
					.last("limit 1"));
			if (Objects.nonNull(obj)) {
				throw new FxzException("当前时间段内存在秒杀活动");
			}
		}

	}

}