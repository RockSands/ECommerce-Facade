package com.ec.auth.utils;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.joda.time.DateTime;

import com.ec.auth.entity.JwtConstants;
import com.ec.auth.entity.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT的工具类,主要生产Token
 * @author bystander
 * @date 2018/10/1
 */
public class JwtUtils {
    /**
     * 生成Token
     * @param userInfo
     * @param privateKey
     * @param expireMinutes
     * @return
     */
    public static String generateToken(UserInfo userInfo, PrivateKey privateKey, int expireMinutes) {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, userInfo.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, userInfo.getName())
                // 过期时间
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                // 加密格式
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 生成Token
     * @param userInfo
     * @param privateKey
     * @param expireMinutes
     * @return
     * @throws Exception
     */
    public static String generateToken(UserInfo userInfo, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, userInfo.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, userInfo.getName())
                .setExpiration(DateTime.now().plus(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.ES256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析Token
     * @param publicKey
     * @param token
     * @return
     */
    public static Jws<Claims> parseToken(PublicKey publicKey, String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }


    /**
     * 公钥解析Token
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parseToken(byte[] publicKey, String token) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey)).parseClaimsJws(token);
    }


    /**
     * 从Token中获取用户信息（使用公钥解析）
     * @param publicKey
     * @param token
     * @return
     */
    public static UserInfo getUserInfo(PublicKey publicKey, String token) {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        return new UserInfo(
                ObjectUtils.toLong(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }

    /**
     * 从Token中获取用户信息（使用公钥解析）
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static UserInfo getUserInfo(byte[] publicKey, String token) throws Exception {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        return new UserInfo(
                ObjectUtils.toLong(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }
}
