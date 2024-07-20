package com.gzasc.onlinecarhailing.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

//生成JWT令牌的工具类。
public class GeneratorJWTUtils {
    //    秘钥
    private static String SECRET = "rz2008011326 Sharing is only supported for boot loader classes because bootstrap classpath has been appended";

    // 加密密钥实例
    private static SecretKey ENCRYPTION_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    //    生成的JWT的有效时间
    private static Date EFFECTIVE_LENGTH = new Date(System.currentTimeMillis() + 259200000L);

    // 生成JWT令牌
//    形参 claims是自定义的内容，一般里面装的是用户特有的信息，如id，，，，
//    不过现在这种方式好像已经被淘汰了，setClaim的方法被划了横线。
    public static String generateJWT(Map<String, Object> claims) {
        // 令牌id
        String uuid = UUID.randomUUID().toString();

        String jwt = Jwts.builder()
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                // 设置自定义的信息，一般是用户唯一的，所以是id
                .claims(claims)
//                JWT还要ID？可能是新版特性
                .id(uuid)
//                设置JWT过期时间，现在它要一个Date类型的参数。
                .expiration(EFFECTIVE_LENGTH)
//                设置是什么时候发的jwt
                .issuedAt(new Date())
//                设置是谁给的JWT
                .issuer("zr")
//                给谁的
                .subject("user")
//                签名，第一个是以后用来解密的密钥，第二个是加密的算法。SignatureAlgorithm.HS256这样的又又被弃用了。
                .signWith(ENCRYPTION_KEY, Jwts.SIG.HS256)
                .compact();


        return jwt;

    }
//   解析token

    public static Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(ENCRYPTION_KEY)
                .build()
                .parseSignedClaims(token);
    }

    //    使用HUTOOL来生成JWT
    public static void testJwt() {
    }

}
