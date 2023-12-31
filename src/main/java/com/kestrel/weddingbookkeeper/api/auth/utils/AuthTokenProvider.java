package com.kestrel.weddingbookkeeper.api.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenProvider {

    @Value("${app.auth.tokenExpiry}")
    private String expiry;

    private final Key key;

    public AuthTokenProvider(@Value("${app.auth.tokenSecret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public AuthToken createToken(Long memberId, String expiry) {
        Date expiryDate = getExpiryDate(expiry);
        return new AuthToken(memberId, expiryDate, key);
    }

    public AuthToken createUserAppToken(Long memberId) {
        return createToken(memberId, expiry);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public static Date getExpiryDate(String expiry) {
        return new Date(System.currentTimeMillis() + Long.parseLong(expiry));
    }

    public Authentication getAuthentication(AuthToken authToken) {

        if (authToken.validate()) {
            Claims claims = authToken.getTokenClaims();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

            Long memberId = Long.valueOf((Integer) claims.get("memberId"));
            WeddingMember weddingMember = new WeddingMember(memberId, authorities);

            return new UsernamePasswordAuthenticationToken(weddingMember, authToken, authorities);
        } else {
            throw new RuntimeException();
        }
    }
}
