package com.learn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.learn.config.TokenConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * token 工具类
 *
 * @author wangxl
 * @date 2021/7/24 17:33
 */
@Component
public class TokenUtil {

    @Autowired
    private TokenConfiguration tokenConfiguration;

    /**
     * 加密token.
     */
    public String getToken(String userId, String userRole) {
        //这个是放到负载payLoad 里面,魔法值可以使用常量类进行封装.
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("userRole", userRole)
                .withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(tokenConfiguration.getPrivateKey()));
    }

    /**
     * 解析token.
     */
    public Map<String, String> parseToken(String token) {
        HashMap<String, String> map = new HashMap<>(16);
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(tokenConfiguration.getPrivateKey()))
                .build().verify(token);
        map.put("userId", decodedjwt.getClaim("userId").asString());
        map.put("userRole", decodedjwt.getClaim("userRole").asString());
        map.put("timeStamp", decodedjwt.getClaim("timeStamp").asLong().toString());
        return map;
    }
}
