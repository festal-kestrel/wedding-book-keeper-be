package com.kestrel.weddingbookkeeper.api.auth.dao;

import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JwtTokenDao {

    int insertToken(JwtToken jwtToken);

}
