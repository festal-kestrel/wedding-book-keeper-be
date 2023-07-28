package com.kestrel.weddingbookkeeper.api.member.service;

import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponseDto;
import com.kestrel.weddingbookkeeper.api.member.dto.response.MemberInformationResponse;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;

public interface MemberService {

    Member getMember(Long memberId);

    Long loginAndGetMemberId(Optional<Member> memberOptional, KakaoResponseDto kakaoResponseDto);

    Optional<Member> findCurrentUser(KakaoResponseDto kakaoResponseDto);

    void markPartnerCodeIssued(Member member);

    MemberInformationResponse getMemberInformation(Long memberId);
}
