package com.kestrel.weddingbookkeeper.api.member.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;

public interface MemberService {

    Member getMember(Integer memberId);
    void registerNewMember(Member member);
    boolean isNewUser(Member member);
}