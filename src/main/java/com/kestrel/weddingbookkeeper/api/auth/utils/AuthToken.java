package com.kestrel.weddingbookkeeper.api.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
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
    private static final String SUBJECT_ID = "festal-kestral";

    AuthToken(String memberId, String roleType, Date expiry, Key key) {
        String role = roleType.toString();
        this.key = key;
        this.token = createAuthToken(SUBJECT_ID, memberId, expiry);
    }

    private String createAuthToken(String subjectId, String memberId, Date expiry) {
        return Jwts.builder()
                .setSubject(subjectId)
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
        } catch (SecurityException e) {
            System.out.println("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
        }
        return null;
    }
}
