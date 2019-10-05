package com.ec.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 
 * Springcloud配置中心，
 * @EnableConfigServer是配置中心的启动注解
 * 
 * http://localhost:8888/config-client/dev  查看配置
 * http://localhost:8888/actuator/bus-refresh  刷新
 * 
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
