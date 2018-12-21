package com.ec.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cors 解决URL跨域问题
 * 
 * 跨域问题是由于ajax引起的,由于ajax可以异步调用且读取cookie,因而一旦跨域且携带cookie将引发安全问提
 * 进而ajax默认是不支持跨域,  这里跨域指: 不同端口、不同前缀、不同一级域名。
 * 注意的是:所有浏览器对ajax进行了该限制
 * 
 * 但目前网站均使用二级域名，因而当二级域名相互调用时会出现跨域问题。所以该问题非常普遍。而cors是当前最好的结局方案.
 * 但是cors需要浏览器支持,目前主流均已经支持. 
 * 其原理是,浏览器发送一个带有cors的请求,查看返回的response是否支持跨域.
 * 
 * @author CKW
 *
 */
@Configuration
public class GlobalCorsConfig {
	@Bean
	public CorsFilter corsfilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://manage.ecommerce.com");
        config.addAllowedOrigin("http://www.ecommerce.com");
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setMaxAge(3600L);// 超时时间,单位秒,在此期间内,浏览器不会再次发送验证权限
        // 4）允许的头信息
        config.addAllowedHeader("*");

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
	}

}
