package com.ec.auth.properties;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ResourceUtils;

import com.ec.auth.utils.RsaUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT的配置文件,主要是定义加密文件
 * @author bystander
 * @date 2018/10/1
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "ec.jwt")
public class JwtProperties {

	private String secret;

	private String pubKeyPath;

	private String priKeyPath;

	private Integer expire;

	private String cookieName;

	private PrivateKey privateKey;

	private PublicKey publicKey;

	private int cookieMaxAge;

	@PostConstruct
	public void init() {
		try {
			String pubKeyFullPath = ResourceUtils.getURL(pubKeyPath).getPath();
			String priKeyFullPath = ResourceUtils.getURL(priKeyPath).getPath();
			// 首先判断公钥私钥是否存在，不存在则先生成公钥私钥
			// if (!pubKey.exists() || !priKey.exists()) {
			// 创建公钥，私钥
			// RsaUtils.generateKey(pubKeyFullPath, priKeyFullPath, secret);
			// }
			// }
			// 公钥私钥都存在
			this.publicKey = RsaUtils.getPublicKey(pubKeyFullPath);
			this.privateKey = RsaUtils.getPrivateKey(priKeyFullPath);
		} catch (Exception e) {
			log.error("初始化公钥私钥失败", e);
			throw new RuntimeException();
		}
	}
}
