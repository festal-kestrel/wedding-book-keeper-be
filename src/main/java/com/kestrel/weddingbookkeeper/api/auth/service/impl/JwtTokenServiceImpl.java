package com.kestrel.weddingbookkeeper.api.auth.service.impl;

import com.kestrel.weddingbookkeeper.api.auth.dao.JwtTokenDao;
import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;
import com.kestrel.weddingbookkeeper.api.auth.exception.JwtTokenNotSavedException;
import com.kestrel.weddingbookkeeper.api.auth.service.JwtTokenService;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenDao jwtTokenDao;

    public JwtTokenServiceImpl(JwtTokenDao jwtTokenDao) {
        this.jwtTokenDao = jwtTokenDao;
    }

    @Override
    public void saveJwtToken(JwtToken jwtToken) {
        boolean isSaved = jwtTokenDao.insertToken(jwtToken) == 1;
        if (!isSaved) {
            throw new JwtTokenNotSavedException();
        }
    }
}
