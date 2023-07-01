package com.kestrel.weddingbookkeeper.api.member.service;

import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponseDto;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;

public interface MemberService {

    Member getMember(Integer memberId);

    int loginAndGetMemberId(Optional<Member> memberOptional, KakaoResponseDto kakaoResponseDto);

    Optional<Member> findCurrentUser(KakaoResponseDto kakaoResponseDto);
}
