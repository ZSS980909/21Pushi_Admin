package com.ershiyi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.ershiyi.config.JwtConfig;
import com.ershiyi.dist.RespEnum;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenUtils {

    public static final String USER_KEY = "userKey";

    public static final String SYSTEM_KEY = "sys";

    public static final String DEVICE_KEY = "device";

    public static final String EXP_KEY = "exp";

    public static final String SCHOOL_ID="schoolId";

    public static final String REFRESH_KEY = "refresh";

    private static JwtConfig jwtConfig;

    private static Algorithm algorithm;

    public TokenUtils(JwtConfig jwtConfig){
        TokenUtils.jwtConfig = jwtConfig;
        algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
    }

    /**
     * 创建token或刷新Token
     * @param userKey 用户唯一标识
     * @param system 系统
     * @param device 设备
     * @return
     */
    public static String createToken(String userKey, String system, String device,String schoolId) {
        Date now = new Date();
        String token = JWT.create()
                //签发人
                .withIssuer(jwtConfig.getIssuer())
                //签发时间
                .withIssuedAt(now)
                //过期时间
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getExpireTime()))
                //自定义的存放的数据
                .withClaim(USER_KEY, userKey)
                .withClaim(SYSTEM_KEY, system)
                .withClaim(DEVICE_KEY, device)
                .withClaim(SCHOOL_ID, schoolId)
                .withClaim(REFRESH_KEY, new Date(now.getTime() + jwtConfig.getRefreshExpireTime()))
                //签名
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token有效性(未验证redis)
     * @param token
     */
    public static void verificationToken(String token){
        try {
            JWT.require(algorithm).build().verify(token);
        }catch (JWTVerificationException e){
            RespEnum.ERROR.throwException(e);
        }
    }

    public static void syncResponseToken(String token){
        HttpServletResponse httpServletResponse = WebUtils.getHttpServletResponse();
        if(httpServletResponse != null){
            httpServletResponse.setHeader(WebUtils.AUTH_KEY, jwtConfig.getHeader().getBearer() + " " + token);
        }
    }

    public static void syncRedis(String token){
        //有效token
        RedisUtils.set(getUserKey(token), token, jwtConfig.getExpireTime() / 1000);
        //token刷新有效期
        RedisUtils.set(token, getLong(token, REFRESH_KEY), jwtConfig.getRefreshExpireTime() / 1000);
    }

    public static void clear(){
        clear(getRequestToken());
    }

    public static void clear(String token){
        RedisUtils.del(getUserKey(token), token);
    }

    public static String getRequestToken(){
        HttpServletRequest httpServletRequest = WebUtils.getHttpServletRequest();
        if(httpServletRequest != null){
            return StringUtils.replace(
                    WebUtils.getHttpHeaders(httpServletRequest).get(WebUtils.AUTH_KEY),
                    jwtConfig.getHeader().getBearer(), "").trim();
        }
        return null;
    }

    public static String getUserKey(){
        return getUserKey(getRequestToken());
    }

    public static String getUserKey(String token){
        if(StringUtils.isNotEmpty(token)){
            return getStr(token, USER_KEY);
        }
        return null;
    }

    public static Claim getClaim(String token, String key){
        return JWT.decode(token).getClaim(key);
    }

    public static String getStr(String token, String key){
        return getClaim(token, key).asString();
    }

    public static Integer getInt(String token, String key){
        return getClaim(token, key).asInt();
    }

    public static Long getLong(String token, String key){
        return getClaim(token, key).asLong();
    }

    public static Date getDate(String token, String key){
        return getClaim(token, key).asDate();
    }
}
