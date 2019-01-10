package com.ec.search.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.commodity.api.BrandApi;

/**
 * 这个API是定义在Interface中的.目标就是由其他工程调用
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
