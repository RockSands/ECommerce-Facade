package com.ec.auth.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.auth.entity.UserInfo;
import com.ec.auth.properties.JwtProperties;
import com.ec.auth.test.properties.AuthService;
import com.ec.auth.utils.JwtUtils;
import com.ec.common.enums.ExceptionEnum;
import com.ec.common.exception.ECException;
import com.ec.common.utils.CookieUtils;

/**
 * @author bystander
 * @date 2018/10/1
 */
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties props;


    /**
     * 登录授权
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String token = authService.login(username, password);
        if (StringUtils.isBlank(token)) {
            throw new ECException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //将Token写入cookie中
        // 由于使用Nginx代理, 所以Nginx的传递的Host可能中途变化,从而导致domain(域)变更
        // Cookie无法跨域,因而cookie设置失败
        // 需要在Nginx上设置 proxy_set_header Host $host  透传Host,保证域名不变
        CookieUtils.newBuilder(response).httpOnly().maxAge(props.getCookieMaxAge()).request(request).build(props.getCookieName(), token);
        return ResponseEntity.ok().build();
    }

    /**
     * 验证用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("LY_TOKEN") String token, HttpServletRequest request, HttpServletResponse response) {
        try {
            //从Token中获取用户信息
            UserInfo userInfo = JwtUtils.getUserInfo(props.getPublicKey(), token);
            //成功，刷新Token
            String newToken = JwtUtils.generateToken(userInfo, props.getPrivateKey(), props.getExpire());
            //将新的Token写入cookie中，并设置httpOnly
            CookieUtils.newBuilder(response).httpOnly().maxAge(props.getCookieMaxAge()).request(request).build(props.getCookieName(), newToken);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            //Token无效
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /**
     * 注销登录
     *
     * @param token
     * @param response
     * @return
     */
    @GetMapping("logout")
    public ResponseEntity<Void> logout(@CookieValue("LY_TOKEN") String token, HttpServletResponse response) {
        if (StringUtils.isNotBlank(token)) {
            CookieUtils.newBuilder(response).maxAge(0).build(props.getCookieName(), token);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
