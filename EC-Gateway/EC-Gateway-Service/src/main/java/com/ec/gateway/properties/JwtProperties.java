package com.ec.gateway.properties;

import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bystander
 * @date 2018/10/2
 */

@ConfigurationProperties(prefix = "ec.jwt")
@Data
@Slf4j
public class JwtProperties {

    private String pubKeyPath;
    private PublicKey publicKey;
    private String cookieName;

    @PostConstruct
    public void init() {
        try {
        	String pubKeyFullPath = ResourceUtils.getURL(pubKeyPath).getPath();
            //获取公钥
            publicKey = RsaUtils.getPublicKey(pubKeyFullPath);
        } catch (Exception e) {
            log.error("初始化公钥失败", e);
            throw new RuntimeException();
        }
    }
}
