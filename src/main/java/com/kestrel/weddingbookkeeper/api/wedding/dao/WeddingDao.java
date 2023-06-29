package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeddingDao {
    Wedding getWeddingInfo(@Param("weddingId") Integer weddingId);
}
