package com.ec.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer 启动Eureka服务注册服务
 */
@SpringBootApplication
@EnableEurekaServer
public class EC_Regidtry {
	// 启动后访问Eureka服务页面，
	public static void main(String[] args) {
		SpringApplication.run(EC_Regidtry.class);
	}
}