package com.kestrel.weddingbookkeeper.api.auth.service.impl;

import com.kestrel.weddingbookkeeper.api.auth.dao.JwtTokenDao;
import com.kestrel.weddingbookkeeper.api.auth.exception.JwtTokenNotSavedException;
import com.kestrel.weddingbookkeeper.api.auth.service.OauthService;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthToken;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;
import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import org.springframework.stereotype.Service;

@Service
public class OauthServiceImpl implements OauthService {

    private final JwtTokenDao jwtTokenDao;

    private final AuthTokenProvider authTokenProvider;

    public OauthServiceImpl(JwtTokenDao jwtTokenDao, MemberDao memberDao, AuthTokenProvider authTokenProvider) {
        this.jwtTokenDao = jwtTokenDao;
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    public JwtToken createAndSaveToken(int memberId) {
        AuthToken authToken = authTokenProvider.createUserAppToken(Integer.toString(memberId));
        JwtToken jwtToken = new JwtToken(authToken.getToken());

        boolean isSaved = jwtTokenDao.insertToken(jwtToken) == 1;
        if (!isSaved) {
            throw new JwtTokenNotSavedException();
        }
        return jwtToken;
    }


}
