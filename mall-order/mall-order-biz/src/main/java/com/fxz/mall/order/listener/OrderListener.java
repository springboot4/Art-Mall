package com.fxz.mall.order.listener;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.mall.order.service.impl.OrderServiceImpl;
import com.fxz.mall.product.feign.RemoteSkuService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 订单超时取消
 *
 * @author fxz
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener {

	private final OrderServiceImpl orderService;

	private final RemoteSkuService skuFeignClient;

	/**
	 * 订单超时未支付，关闭订单，释放库存
	 */
	@RabbitListener(queues = "order.close.queue")
	public void closeOrder(String orderToken, Message message, Channel channel) {
		log.info("=======================订单超时未支付，开始系统自动关闭订单=======================");
		try {
			if (orderService.closeOrder(orderToken)) {
				log.info("=======================关闭订单成功，开始释放已锁定的库存=======================");
				skuFeignClient.unlockStock(orderToken, SecurityConstants.FROM_IN);
			}
			else {
				log.info("=======================关单失败，订单状态已处理，手动确认消息处理完毕=======================");
				// basicAck(tag,multiple)，multiple为true开启批量确认，小于tag值队列中未被消费的消息一次性确认
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
			}
		}
		catch (IOException e) {
			log.info("=======================系统自动关闭订单消息消费失败，重新入队=======================");
			try {
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
			}
			catch (Exception ioException) {
				log.error("系统关单失败");
			}
		}
	}

}
