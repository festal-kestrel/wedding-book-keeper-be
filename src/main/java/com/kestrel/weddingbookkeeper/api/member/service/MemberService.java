package com.kestrel.weddingbookkeeper.api.member.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;

public interface MemberService {

    Member getMember(Integer memberId);
    int registerNewMember(Member member);
    Optional<Member> findCurrrentUser(Member member);
}
