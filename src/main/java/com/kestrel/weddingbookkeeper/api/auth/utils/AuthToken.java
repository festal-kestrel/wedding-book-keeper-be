package com.kestrel.weddingbookkeeper.api.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthToken {


    @Getter
    private final String token;
    private final Key key;

    private static final String MEMBER_ID = "memberId";

    AuthToken(String memberId, String roleType, Date expiry, Key key) {
        String role = roleType.toString();
        this.key = key;
        this.token = createAuthToken("festal-kestral", memberId, expiry);
    }

    private String createAuthToken(String socialId, String memberId, Date expiry) {
        return Jwts.builder()
                .setSubject(socialId)
                .claim(MEMBER_ID, memberId)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    public boolean validate() {
        return this.getTokenClaims() != null;
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
