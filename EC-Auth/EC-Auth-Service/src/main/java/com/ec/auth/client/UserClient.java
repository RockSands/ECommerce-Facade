package com.ec.auth.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.user.api.UserApi;

/**
 * Feign接口,调用用户接口
 * @author bystander
 * @date 2018/10/1
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
