package com.ec.search.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.commodity.api.SpecApi;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("commodity-service")
public interface SpecClient extends SpecApi {
}
