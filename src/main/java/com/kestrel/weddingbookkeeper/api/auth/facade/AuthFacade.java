package com.kestrel.weddingbookkeeper.api.auth.facade;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerifyPartnerVerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.service.AuthService;
import com.kestrel.weddingbookkeeper.api.auth.vo.PartnerVerificationCode;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthFacade {

    private final MemberService memberService;
    private final AuthService authService;
    private final WeddingService weddingService;

    public AuthFacade(final MemberService memberService, final AuthService authService, final WeddingService weddingService) {
        this.memberService = memberService;
        this.authService = authService;
        this.weddingService = weddingService;
    }

    @Transactional
    public VerificationCodeResponse getPartnerVerificationCode(Long memberId) {
        Member member = memberService.getMember(memberId);
        PartnerVerificationCode verificationCode = authService.getPartnerVerificationCode(member);
        return new VerificationCodeResponse(verificationCode.getVerificationCode());
    }

    @Transactional
    public VerifyPartnerVerificationCodeResponse verifyPartnerVerificationCode(Long memberId, VerificationCodeRequest verificationCodeRequest) {
        Long partnerId = authService.verifyPartnerVerificationCode(verificationCodeRequest);
        Member member = memberService.getMember(memberId);
        Member partner = memberService.getMember(partnerId);
        memberService.assertGenderMismatch(member, partner);
        Long weddingId = weddingService.registerPartner(member, partner);
        return new VerifyPartnerVerificationCodeResponse(weddingId);
    }
}
