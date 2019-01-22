package com.ec.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author bystander
 * @date 2018/10/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.ec.pay.mapper")
public class ECPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECPayApplication.class, args);
    }
}
