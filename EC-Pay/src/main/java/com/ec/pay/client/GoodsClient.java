package com.ec.pay.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.commodity.api.GoodsApi;

/**
 * @author bystander
 * @date 2018/10/4
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
