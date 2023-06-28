package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeddingDao {

    Wedding getWeddingInfo();
}
