package com.fxz.xxlJob.execute.impl.promotion;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.jackson.util.JacksonUtil;
import com.fxz.mall.promotion.entity.Seckill;
import com.fxz.mall.promotion.entity.SeckillSetting;
import com.fxz.mall.promotion.entity.Setting;
import com.fxz.mall.promotion.enums.SettingEnum;
import com.fxz.mall.promotion.feign.RemoteSeckillService;
import com.fxz.mall.promotion.feign.RemoteSettingService;
import com.fxz.xxlJob.execute.EveryDayExecute;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 促销商品每日定时器
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 11:25
 */
@Component
@RequiredArgsConstructor
public class PromotionEverydayExecute implements EveryDayExecute {

	/**
	 * 预创建活动数量
	 */
	private final Integer PRE_CREATION = 7;

	private final RemoteSeckillService remoteSeckillService;

	private final RemoteSettingService remoteSettingService;

	Logger logger = LoggerFactory.getLogger(PromotionEverydayExecute.class);

	@Override
	public void execute() {
		logger.info("更新促销商品信息");

		// 新增秒杀商品活动
		this.saveSeckill();
	}

	/**
	 * 默认创建7天的秒杀活动
	 */
	private void saveSeckill() {
		logger.info("获取秒杀规则");
		// 获取秒杀规则
		Setting setting = remoteSettingService.findSetting(SettingEnum.SECKILL_SETTING).getData();
		if (Objects.isNull(setting)) {
			throw new FxzException("秒杀活动设置为空");
		}
		logger.info("秒杀规则:{}", setting);

		// 构建七天的秒杀活动信息
		ArrayList<Seckill> seckills = new ArrayList<>();
		SeckillSetting seckillSetting = JacksonUtil.parseObject(setting.getSettingValue(), SeckillSetting.class);
		for (int i = 1; i <= this.PRE_CREATION; i++) {
			Seckill seckill = new Seckill(i, seckillSetting.getHours(), seckillSetting.getSeckillRule());
			seckills.add(seckill);
		}

		logger.info("保存秒杀活动:{}", seckills);
		// 调用远程接口保存秒杀活动
		remoteSeckillService.saveSeckill(seckills, SecurityConstants.FROM_IN);
	}

}
