package com.ec.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author bystander
 * @date 2018/10/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ECCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECCartApplication.class, args);
    }
}
