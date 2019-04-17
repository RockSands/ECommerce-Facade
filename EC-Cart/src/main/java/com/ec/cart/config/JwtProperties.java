package com.ec.cart.config;

import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;

import com.ec.auth.utils.RsaUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 鉴权的配置文件
 * @author bystander
 * @date 2018/10/3
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "ec.jwt")
public class JwtProperties {

    private String pubKeyPath;

    private String cookieName;

    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        try {
        	String pubKeyFullPath = ResourceUtils.getURL(pubKeyPath).getPath();
            publicKey = RsaUtils.getPublicKey(pubKeyFullPath);
        } catch (Exception e) {
            log.error("初始化公钥失败", e);
            throw new RuntimeException();

        }
    }
}
