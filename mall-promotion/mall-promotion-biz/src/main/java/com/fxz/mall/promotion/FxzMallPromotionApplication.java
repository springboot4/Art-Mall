package com.fxz.mall.promotion;

import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/9 11:19
 */
@EnableFxzCloudResourceServer
@EnableFeignClients(basePackages = { "com.fxz" })
@MapperScan("com.fxz.mall.promotion.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class FxzMallPromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzMallPromotionApplication.class, args);
	}

}
