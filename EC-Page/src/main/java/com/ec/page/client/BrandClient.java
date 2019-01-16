package com.ec.page.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.commodity.api.BrandApi;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
