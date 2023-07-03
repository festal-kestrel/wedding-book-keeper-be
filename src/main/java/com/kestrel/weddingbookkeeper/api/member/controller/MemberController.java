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

    private static final Integer MEMBER_ID = 18;

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public void getMemberTest() {
//    public void getMemberTest(@AuthenticationPrincipal WeddingMember weddingMember) {
//        Long memberId = weddingMember.getMemberId();
    }

    @GetMapping("/me")
    public MemberInformationResponse getMemberInformation(@AuthenticationPrincipal WeddingMember weddingMember) {
        return memberService.getMemberInformation(MEMBER_ID);
    }
}
