package com.fxz.mall.order.config;

import com.fxz.common.core.factory.NamedThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 17:57
 */
@Configuration
public class ThreadPoolConfig {

	@Bean
	public ThreadPoolExecutor threadPoolExecutor() {
		return new ThreadPoolExecutor(50, 500, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000),
				new NamedThreadFactory("订单线程"));
	}

}
