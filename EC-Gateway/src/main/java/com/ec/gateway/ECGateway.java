package com.ec.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.ec.gateway.filter.LoginFilter;
import com.ec.gateway.filter.factory.RequestTimeGatewayFilterFactory;

@EnableDiscoveryClient
@SpringCloudApplication
public class ECGateway {
	public static void main(String[] args) {
		SpringApplication.run(ECGateway.class, args);
	}

	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}

	@Bean
	public LoginFilter loginFilter() {
		return new LoginFilter();
	}
}
