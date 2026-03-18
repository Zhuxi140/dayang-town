package com.dayang.util;

import cn.hutool.core.util.StrUtil;
import com.dayang.constant.Enum.ErrorCodeEnum;
import com.dayang.exception.NotLoginException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author zhuxi
 * @apiNote JWT工具类
 */


@Slf4j
public class JwtUtil {

    // TODO: 密钥以及时间应存放于yaml(xml)文件中，通过@Value等获取
    private static final String JWT_SECRET = "DaYangTown_Super_Secret_Key_2026_For_JWT_Auth!!!";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;


    /**
     * 创建Token
     * @param userId 用户Id
     * @return Token
     */
    public static String createToken(Long userId){

        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        Date exp = new Date(nowMills + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(now)
                .expiration(exp)
                .signWith(SECRET_KEY)
                .compact();
    }


    /**
     * 解析Token
     * @param token Token
     * @return Claims
     */
    public static Claims parseToken(String token){
        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (ExpiredJwtException e){
            throw new NotLoginException(ErrorCodeEnum.TOKEN_EXPIRED.getMsg());
        }catch (JwtException | IllegalArgumentException e) {
            throw new NotLoginException(ErrorCodeEnum.TOKEN_INVALID.getMsg());
        }
    }


    /**
     * 获取用户Id
     * @param token Token
     * @return 用户Id
     */
    public static Long getUserId(String token){
        Claims claims = parseToken(token);
        String userId = claims.getSubject();
        // 防御性检查  避免用户Id为空或非数字
        if (StrUtil.isNotBlank(userId) && StrUtil.isNumeric(userId)){
            return Long.valueOf(userId);
        }
        throw new NotLoginException(ErrorCodeEnum.TOKEN_INVALID.getMsg());
    }

}
