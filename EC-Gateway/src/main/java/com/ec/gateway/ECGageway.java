package com.ec.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringCloudApplication
public class ECGageway {
	public static void main(String[] args) {
		SpringApplication.run(ECGageway.class, args);
	}
}
