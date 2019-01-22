package com.ec.gateway.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author bystander
 * @date 2018/10/2
 */
@Data
@ConfigurationProperties(prefix = "ec.filter")
public class FilterProperties {

    private List<String> allowPaths;


}
