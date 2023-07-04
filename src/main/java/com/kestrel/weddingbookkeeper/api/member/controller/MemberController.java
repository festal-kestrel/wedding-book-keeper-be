package com.kestrel.weddingbookkeeper.api.member.controller;

import com.kestrel.weddingbookkeeper.api.auth.utils.WeddingMember;
import com.kestrel.weddingbookkeeper.api.member.dto.response.MemberInformationResponse;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/me")
    public MemberInformationResponse getMemberInformation(@AuthenticationPrincipal final WeddingMember weddingMember) {
        return memberService.getMemberInformation(weddingMember.getMemberId());
    }
}
