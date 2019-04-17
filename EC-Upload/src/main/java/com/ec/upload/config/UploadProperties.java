package com.ec.upload.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 上传的属性配置
 * @author bystander
 * @date 2018/9/16
 */
@Data
@ConfigurationProperties(prefix = "ecommerce.upload")
@Component
public class UploadProperties {

    private String baseUrl;
    private List<String> allowTypes;
}
