package com.fxz.mall.search.listener;

import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.exception.FxzException;
import com.fxz.mall.search.entity.EsOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fxz
 */
@Slf4j
@Component
public class OrderCanalListener extends BaseCanalBinlogEventProcessor<EsOrder> {

	/**
	 * 插入
	 */
	@Override
	protected void processInsertInternal(CanalBinLogResult<EsOrder> result) {
		log.info("插入:{}", result);
	}

	/**
	 * 更新
	 */
	@Override
	protected void processUpdateInternal(CanalBinLogResult<EsOrder> result) {
		log.info("更新:{}", result);
	}

	/**
	 * 删除
	 */
	@Override
	protected void processDeleteInternal(CanalBinLogResult<EsOrder> result) {
		log.info("删除:{}", result);
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			throw new FxzException("异常", throwable);
		};
	}

}
