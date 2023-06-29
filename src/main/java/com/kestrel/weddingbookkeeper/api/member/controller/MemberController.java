package com.kestrel.weddingbookkeeper.api.member.controller;

import com.kestrel.weddingbookkeeper.api.auth.utils.WeddingMember;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @GetMapping
    public void getMemberTest(@AuthenticationPrincipal WeddingMember weddingMember) {
        Long memberId = weddingMember.getMemberId();
        System.out.println("memberId = " + memberId);
    }
}
