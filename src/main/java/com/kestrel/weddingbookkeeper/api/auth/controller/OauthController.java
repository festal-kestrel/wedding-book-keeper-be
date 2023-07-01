package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kestrel.weddingbookkeeper.api.auth.service.OauthService;
import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;
import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponseDto;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.utils.KakaoUtil;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Oauth API", description = "로그인 처리 API")
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

    @Operation(summary = "유저 인증", description = "유저의 카카오 토큰으로 해당 유저의 프로필 정보를 저장 하고 jwt 토큰을 발행 후 반환", tags = {"Oauth API"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(schema = @Schema(implementation = JwtToken.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
    })
    @Parameters({
            @Parameter(name="token", description="카카오 api에서 인가를 받은 후 발행된 토큰")
    })
    @PostMapping("/authorize")
    public ResponseEntity<JwtToken> getMemberInfo(@RequestParam String token) throws JsonProcessingException {

        KakaoResponseDto kakaoResponseDto = kakaoUtil.getUserInfo(token);
        Optional<Member> memberOptional = memberService.findCurrentUser(kakaoResponseDto);
        int memberId = memberService.loginAndGetMemberId(memberOptional, kakaoResponseDto);
        JwtToken jwtToken = oauthService.createAndSaveToken(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }


}
