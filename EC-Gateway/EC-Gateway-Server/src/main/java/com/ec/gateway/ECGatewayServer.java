package com.ec.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * 自定义负载均衡 :https://blog.csdn.net/cyxinda/article/details/98884205
 * @author Administrator
 *
 */
@EnableDiscoveryClient
@SpringCloudApplication
public class ECGatewayServer {
	public static void main(String[] args) {
		SpringApplication.run(ECGatewayServer.class, args);
	}
}
