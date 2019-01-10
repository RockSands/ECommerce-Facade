package com.ec.search.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.commodity.api.CategoryApi;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("commodity-service")
public interface CategoryClient extends CategoryApi {
}
