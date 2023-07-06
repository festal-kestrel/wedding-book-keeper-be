package com.kestrel.weddingbookkeeper.api.member.controller;

import com.kestrel.weddingbookkeeper.api.auth.utils.WeddingMember;
import com.kestrel.weddingbookkeeper.api.member.dto.response.MemberInformationResponse;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Members API", description = "멤버 관련 API")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary="유저 정보 호출", tags = {"Members API"})
    @GetMapping("/me")
    public MemberInformationResponse getMemberInformation(@AuthenticationPrincipal final WeddingMember weddingMember) {
        return memberService.getMemberInformation(weddingMember.getMemberId());
    }
}
