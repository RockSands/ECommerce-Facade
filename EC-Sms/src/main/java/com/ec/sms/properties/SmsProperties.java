package com.ec.sms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author bystander
 * @date 2018/9/29
 */
@Data
@ConfigurationProperties(prefix = "ecommerce.sms")
public class SmsProperties {

    String accessKeyId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;
}
