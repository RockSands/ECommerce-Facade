package com.ec.registry.common;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer 启动Eureka服务注册服务
 */
@EnableEurekaServer
@SpringBootApplication
public class EC_Regidtry {
	// 启动后访问Eureka服务页面，
	public static void main(String[] args) {
		new SpringApplicationBuilder(EC_Regidtry.class).web(true).run(args);
	}
}