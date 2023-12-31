package com.kestrel.weddingbookkeeper.api.member.dao;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    Optional<Member> selectById(Long id);

    int insertMember(Member member);

    Optional<Member> selectByEmail(Member member);

    int updatePartnerCodeIssued(Member member);

}
