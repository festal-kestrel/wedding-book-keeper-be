package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kestrel.weddingbookkeeper.api.auth.service.OauthService;
import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;
import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponseDto;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.utils.KakaoUtil;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth")
public class OauthController {

    private final KakaoUtil kakaoUtil;
    private final MemberService memberService;
    private final OauthService oauthService;
    private final AuthTokenProvider authTokenProvider;

    public OauthController(KakaoUtil kakaoUtil, MemberService memberService, OauthService oauthService,
                           AuthTokenProvider authTokenProvider) {
        this.kakaoUtil = kakaoUtil;
        this.memberService = memberService;
        this.oauthService = oauthService;
        this.authTokenProvider = authTokenProvider;
    }

    @PostMapping("/authorize")
    public ResponseEntity<JwtToken> getMemberInfo(@RequestParam String token) throws JsonProcessingException {

        KakaoResponseDto kakaoResponseDto = kakaoUtil.getUserInfo(token);
        Optional<Member> memberOptional = memberService.findCurrentUser(kakaoResponseDto);
        int memberId = memberService.loginAndGetMemberId(memberOptional, kakaoResponseDto);
        JwtToken jwtToken = oauthService.createAndSaveToken(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }


}
