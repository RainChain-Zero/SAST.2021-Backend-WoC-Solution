package com.example.woc.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Map;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/12 20:55
 */
public class JWTUtils {
    //盐
    private static String SECRET = "kmJoFi^%ut$@V&xg$";

    /**
     * 生产token
     */
    public static String getToken(Map<String, Object> map) {
        //创建JWT builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v.toString());
        });

        //有效时间60s
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 60);

        //设定token的过期时间
        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(SECRET));   //签名 创建token
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token) {
        //如果有任何验证异常，此处都会抛出异常
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
