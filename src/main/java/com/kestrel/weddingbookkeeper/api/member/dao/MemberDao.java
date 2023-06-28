package com.kestrel.weddingbookkeeper.api.member.dao;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    Member selectById(Integer id);
}
