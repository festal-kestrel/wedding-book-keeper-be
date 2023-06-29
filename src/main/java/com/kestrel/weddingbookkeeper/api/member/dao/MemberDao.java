package com.kestrel.weddingbookkeeper.api.member.dao;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    Optional<Member> selectById(Integer id);
    int insertMember(Member member);
    Optional<Member> selectByEmail(Member member);
}