package com.ec.page.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("commodity-service")
public interface GoodsClient extends com.ec.commodity.api.GoodsApi {
}
