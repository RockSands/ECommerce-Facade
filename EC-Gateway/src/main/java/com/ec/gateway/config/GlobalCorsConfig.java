package com.ec.gateway.config;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * Cors 解决URL跨域问题
 * 
 * 跨域问题是由于ajax引起的,由于ajax可以异步调用且读取cookie,因而一旦跨域且携带cookie将引发安全问提 进而ajax默认是不支持跨域,
 * 这里跨域指: 不同端口、不同前缀、不同一级域名。 注意的是:所有浏览器对ajax进行了该限制
 * 
 * 但目前网站均使用二级域名，因而当二级域名相互调用时会出现跨域问题。所以该问题非常普遍。而cors是当前最好的结局方案.
 * 但是cors需要浏览器支持,目前主流均已经支持. 其原理是,浏览器发送一个带有cors的请求,查看返回的response是否支持跨域.
 * 
 * @author CKW
 *
 */
@Configuration
public class GlobalCorsConfig {

//	private static final List<String> ALLOWED_HEADERS = Arrays.asList("x-requested-with", "authorization",
//			"Content-Type", "Authorization", "credential", "X-XSRF-TOKEN");
	private static final String ALLOWED_HEADERS = "*";
	private static final String ALLOWED_METHODS = "*";
	private static final String ALLOWED_ORIGIN = "*";
	private static final String ALLOWED_EXPOSE = "*";
	private static final String MAX_AGE = "18000L";

	@Bean
	public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) -> {
			ServerHttpRequest request = ctx.getRequest();
			if (CorsUtils.isCorsRequest(request)) {
				HttpHeaders requestHeaders = request.getHeaders();
				ServerHttpResponse response = ctx.getResponse();
				HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
				HttpHeaders headers = response.getHeaders();
				/*
				 * 如果是多个可以使用addAll
				 */
//				headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
//						requestHeaders.getAccessControlRequestHeaders());
				// 组织
				headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALLOWED_ORIGIN);
				// Header
				headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALLOWED_HEADERS);
				// Method
				if (requestMethod != null) {
					headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
				}
				headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
				headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALLOWED_EXPOSE);
				headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
				if (request.getMethod() == HttpMethod.OPTIONS) {
					response.setStatusCode(HttpStatus.OK);
					return Mono.empty();
				}
			}
			return chain.filter(ctx);
		};
	}

	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		return new DefaultServerCodecConfigurer();
	}

	/**
	 *
	 * 如果使用了注册中心（如：Eureka），进行控制则需要增加如下配置
	 */
	@Bean
	public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient,
			DiscoveryLocatorProperties properties) {
		return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
	}
}
