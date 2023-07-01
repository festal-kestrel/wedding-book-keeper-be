package com.kestrel.weddingbookkeeper.api.auth.service;

import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;

public interface OauthService {

    JwtToken createAndSaveToken(int memberId);
}
