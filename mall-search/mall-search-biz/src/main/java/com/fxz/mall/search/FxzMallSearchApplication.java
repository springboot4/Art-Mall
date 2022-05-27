package com.fxz.mall.search;

import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/27 21:53
 */
@EnableFxzCloudResourceServer
@EnableFeignClients(basePackages = { "com.fxz" })
@MapperScan("com.fxz")
@EnableDiscoveryClient
@SpringBootApplication
public class FxzMallSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzMallSearchApplication.class, args);
	}

}
