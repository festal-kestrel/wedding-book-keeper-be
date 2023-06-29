package com.kestrel.weddingbookkeeper.api.auth.service;

import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;

public interface JwtTokenService {

    void saveJwtToken(JwtToken jwtToken);
}
