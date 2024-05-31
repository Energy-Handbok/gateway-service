package com.khaphp.gateway_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtHelper {
    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    @Value("${security.secret_key}")
    private String secretKey;

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public String getRoles(String token) {
        Claims claims = getClaims(token);
        return claims.get("roles", String.class);     //bên tạo token ấy (login create token), lúc gắn role vào att thì att đó tên là "roles"
                                                            //ne giờ để lấy các role thì phải lấy từ att tên là "roles"
    }

    public boolean isTokenExpired(String token){
        Claims claims = getClaims(token);
        Date date = claims.getExpiration();
        return date.before(new Date());
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = secretKey.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
