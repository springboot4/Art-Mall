package com.fxz.xxlJob.handler;

import com.fxz.xxlJob.execute.EveryDayExecute;
import com.fxz.xxlJob.execute.EveryHourExecute;
import com.fxz.xxlJob.execute.EveryMinuteExecute;
import com.fxz.xxlJob.execute.EverySecondExecute;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * xxl-job定时任务
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 11:12
 */
@Component
@RequiredArgsConstructor
public class XxlJobHandler {

	Logger logger = LoggerFactory.getLogger(XxlJobHandler.class);

	private final List<EveryDayExecute> everyDayExecutes;

	private final List<EveryHourExecute> everyHourExecutes;

	private final List<EveryMinuteExecute> everyMinuteExecutes;

	private final List<EverySecondExecute> everySecondExecutes;

	/**
	 * 每秒任务执行器
	 */
	@XxlJob("everySecondExecutes")
	public ReturnT<String> everySecondExecutes() {
		logger.info("每秒任务开始执行");

		// 任务为空则不执行
		if (CollectionUtils.isEmpty(everySecondExecutes)) {
			logger.info("每秒任务为空，不执行");
			return ReturnT.SUCCESS;
		}

		// 执行每秒任务
		everySecondExecutes.forEach(EverySecondExecute::execute);

		return ReturnT.SUCCESS;
	}

	/**
	 * 每分钟任务执行器
	 */
	@XxlJob("everyMinuteExecutes")
	public ReturnT<String> everyMinuteExecutes() {
		logger.info("每分钟任务开始执行");

		// 任务为空则不执行
		if (CollectionUtils.isEmpty(everyMinuteExecutes)) {
			logger.info("每分钟任务为空，不执行");
			return ReturnT.SUCCESS;
		}

		// 执行每分钟任务
		everyMinuteExecutes.forEach(EveryMinuteExecute::execute);

		return ReturnT.SUCCESS;
	}

	/**
	 * 每小时任务执行器
	 */
	@XxlJob("everyHourExecutes")
	public ReturnT<String> everyHourExecutes() {
		logger.info("每小时任务开始执行");

		// 任务为空则不执行
		if (CollectionUtils.isEmpty(everyHourExecutes)) {
			logger.info("每小时任务为空，不执行");
			return ReturnT.SUCCESS;
		}

		// 执行每小时任务
		everyHourExecutes.forEach(EveryHourExecute::execute);

		return ReturnT.SUCCESS;
	}

	/**
	 * 每天任务执行器
	 */
	@XxlJob("everyDayExecutes")
	public ReturnT<String> everyDayExecutes() {
		logger.info("每天任务开始执行");

		// 任务为空则不执行
		if (CollectionUtils.isEmpty(everyDayExecutes)) {
			logger.info("每天任务为空，不执行");
			return ReturnT.SUCCESS;
		}

		// 执行每天任务
		everyDayExecutes.forEach(EveryDayExecute::execute);

		return ReturnT.SUCCESS;
	}

}
