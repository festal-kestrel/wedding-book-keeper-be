package com.kestrel.weddingbookkeeper.api.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "jwt 토큰")
public class JwtToken {

    @Schema(description = "jwt 토큰 값")
    private String jwtToken;

    public JwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
