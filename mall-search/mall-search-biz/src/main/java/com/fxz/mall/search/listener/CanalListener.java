package com.fxz.mall.search.listener;

import com.fxz.common.canal.CanalBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author fxz
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CanalListener {

	private final CanalBase canalBase;

	@RabbitListener(queues = "canal_queue")
	public void onMessage(Message message) {
		String result = new String(message.getBody());

		log.info("message:{}", result);

		canalBase.process(result);
	}

}