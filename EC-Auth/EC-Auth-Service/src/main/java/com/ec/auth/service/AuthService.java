package com.ec.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.ec.auth.client.UserClient;
import com.ec.auth.entity.UserInfo;
import com.ec.auth.properties.JwtProperties;
import com.ec.auth.utils.JwtUtils;
import com.ec.common.enums.ExceptionEnum;
import com.ec.common.exception.ECException;
import com.ec.user.pojo.User;

import lombok.extern.slf4j.Slf4j;

/**
 * 鉴权的service,生产Token
 * @author bystander
 * @date 2018/10/1
 */
@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties props;

    public String login(String username, String password) {
        try {
            User user = userClient.queryUser(username, password);
            if (user == null) {
                return null;
            }
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            //生成Token
            String token = JwtUtils.generateToken(userInfo, props.getPrivateKey(), props.getExpire());
            return token;
        } catch (Exception e) {
            log.error("【授权中心】用户名和密码错误，用户名：{}", username,e);
            throw new ECException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }
}
