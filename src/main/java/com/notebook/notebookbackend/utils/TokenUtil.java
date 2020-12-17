package com.notebook.notebookbackend.utils;

import com.notebook.notebookbackend.data.database.DO.UserDO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
public class TokenUtil {
    /**
     * 私钥
     */
    private static final String SECRET = "noteBook";

    /**
     * 过期时间 24h * 60m * 60S
     */
    private static final long TTL = (24 * 60 * 60L);

    /**
     * 请求头参数名
     */
    private static final String TOKEN_HEADER = "Authorization";

    /**
     * 传入一个 String 的 subject ，返回他的加密token
     *
     * @param subject 一般是 userName
     * @return token
     */
    public static String createJwt(String subject) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + TTL * 1000);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET);
        return builder.compact();
    }

    /**
     * 该方法可能有问题，谨慎使用
     */
    public static String createJwt(String subject, Object vo) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + TTL * 1000);
        Map<String, Object> value = new HashMap<>(1);
        value.put(subject, vo);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                //设置主题
                .setSubject(subject)
                .setClaims(value)
                //设置发布时间
                .setIssuedAt(now)
                //设置过期时间
                .setExpiration(expireDate)
                //使用 HS256加密(使用ES256有bug，待解决)
                .signWith(SignatureAlgorithm.HS256, SECRET);
        return builder.compact();
    }

    /**
     * 解析 token
     *
     * @param token token
     * @return token信息
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 刷新 token
     *
     * @param token token
     * @return 新的 token
     */
    public static String refreshToken(String token) throws BusinessException {
        if (isExpired(token)) {
            MyLog.info("Failed To Refresh Token,Cause It Is Expired");
            throw new BusinessException(EmBusinessErr.FAILED_TO_FRESH_TOKEN);
        }
        Claims claims = parseToken(token);
        if (claims != null) {
            String subject = claims.getSubject();
            Object vo = claims.get(subject);
            return createJwt(subject, vo);
        }
        throw new BusinessException(EmBusinessErr.FAILED_TO_FRESH_TOKEN);
    }

    /**
     * 判断是否过期
     *
     * @param token token
     * @return 是否过期
     */
    public static boolean isExpired(String token) {
        try {
            if (token != null) {
                Claims claims = parseToken(token);
                if (claims != null) {
                    Date expiration = claims.getExpiration();
                    return expiration.before(new Date());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }


    /**
     * 判断是否过期
     *
     * @param expireDate 过期时间
     * @return 是否过期
     */
    public static boolean isExpired(Date expireDate) {
        try {
            return expireDate.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 获取 token 参数名
     *
     * @return token 参数名
     */
    public static String getTokenHeader() {
        return TOKEN_HEADER;
    }

    /**
     * 从 token 中获取用户名
     *
     * @param token token
     * @return 用户名
     */
    public static String getUserNameFromToken(String token) throws BusinessException {
        Claims claims = parseToken(token);
        if (claims != null) {
            String subject = claims.getSubject();
            if (subject != null) {
                return subject;
            }
        }
        throw new BusinessException(EmBusinessErr.FAILED_TO_PARSE_TOKEN);
    }
}
