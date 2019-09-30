package com.ec.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer 启动Eureka服务注册服务
 * 
 * 由于是服务注册中心,因而不能动态通过bus+config动态加载
 */
@SpringBootApplication
@EnableEurekaServer
public class ECRegidtry {
	// 启动后访问Eureka服务页面，
	public static void main(String[] args) {
		SpringApplication.run(ECRegidtry.class);
	}
}