package com.kestrel.weddingbookkeeper.api.auth.facade.impl;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.facade.AuthFacade;
import com.kestrel.weddingbookkeeper.api.auth.service.AuthService;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthFacadeImpl implements AuthFacade {

    private final MemberService memberService;
    private final AuthService authService;

    public AuthFacadeImpl(final MemberService memberService, final AuthService authService) {
        this.memberService = memberService;
        this.authService = authService;
    }

    @Override
    @Transactional
    public VerificationCodeResponse getPartnerVerificationCode(Integer memberId) {
        Member member = memberService.getMember(memberId);
        String verificationCode = authService.getPartnerVerificationCode(member);
        if (!member.isPartnerCodeIssued()) {
            memberService.markPartnerCodeIssued(member);
        }
        return new VerificationCodeResponse(verificationCode);
    }

    @Override
    public void verifyPartnerVerificationCode(Integer memberId, VerificationCodeRequest verificationCodeRequest) {
        Member member = memberService.getMember(memberId);
        authService.verifyPartnerVerificationCode(member, verificationCodeRequest);
    }
}
