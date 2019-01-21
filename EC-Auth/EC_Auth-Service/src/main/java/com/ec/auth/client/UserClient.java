package com.ec.auth.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ec.user.api.UserApi;

/**
 * @author bystander
 * @date 2018/10/1
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
