package com.ec.search.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.commodity.api.GoodsApi;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
