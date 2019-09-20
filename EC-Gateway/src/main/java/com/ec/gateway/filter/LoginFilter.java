package com.ec.gateway.filter;

import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.ec.gateway.properties.FilterProperties;
import com.ec.gateway.properties.JwtProperties;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 鉴权拦截器 Spring Cloud Gateway分为面向单路由（GatewayFilter）、面向全部路由（GlobalFilter）
 * Gateway提供了Filter,可以在application.yml配置,比如AddRequestHeaderGatewayFilterFactory
 * 
 * GlobalFilter --> 作用于每一个Router,因而不需要再router上配置即生效,
 * 该类在工程的启动类中注入到Spring Ioc容器中
 * 
 * @author bystander
 * @date 2018/10/2
 */
@Slf4j
@EnableConfigurationProperties({ JwtProperties.class, FilterProperties.class })
public class LoginFilter implements GlobalFilter, Ordered {

	@Autowired
	private JwtProperties props;

	@Autowired
	private FilterProperties filterProps;

	/*
	 * Filter排序,越低越前执行
	 */
	@Override
	public int getOrder() {
		return -100;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		URI url = exchange.getRequest().getURI();
		// 白名单
		if (isAllowPath(url.getPath())) {
			// 继续执行
			return chain.filter(exchange);
		}
		HttpCookie tokenCookie = exchange.getRequest().getCookies().getFirst(props.getCookieName());
		if (tokenCookie == null || StringUtils.isBlank(tokenCookie.getValue())) {
			log.info("token is empty...");
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	/**
	 * 判断请求URI是不是白名单中的URI
	 *
	 * @param requestURI
	 * @return
	 */
	private boolean isAllowPath(String requestURI) {
		boolean flag = false;
		for (String allowPath : filterProps.getAllowPaths()) {
			if (requestURI.startsWith(allowPath)) {
				// 允许
				flag = true;
				break;
			}
		}
		return flag;
	}

}
