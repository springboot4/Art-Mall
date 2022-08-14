package com.fxz.xxlJob;

import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/8/14 11:27
 */
@EnableDiscoveryClient
@EnableFxzCloudResourceServer
@EnableFeignClients(basePackages = { "com.fxz" })
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class XxlJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxlJobApplication.class, args);
	}

}
