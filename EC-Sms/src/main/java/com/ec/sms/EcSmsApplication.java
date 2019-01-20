package com.ec.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author bystander
 * @date 2018/9/29
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EcSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcSmsApplication.class);
    }
}
