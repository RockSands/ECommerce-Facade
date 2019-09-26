package com.ec.gateway.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 过滤的配置文件
 * @author bystander
 * @date 2018/10/2
 */
@Data
@ConfigurationProperties(prefix = "ec.filter")
public class FilterProperties {

    private List<String> allowPaths;


}
