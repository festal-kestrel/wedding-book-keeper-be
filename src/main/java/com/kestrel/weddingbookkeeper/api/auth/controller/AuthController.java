package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponse;
import com.kestrel.weddingbookkeeper.api.auth.dto.TokenResponse;
import com.kestrel.weddingbookkeeper.api.auth.utils.KakaoUtil;
//import java.net.http.HttpHeaders;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth")
public class AuthController {

    private final KakaoUtil kakaoUtil;
    private final MemberService memberService;

    public AuthController(KakaoUtil kakaoUtil, MemberService memberService) {
        this.kakaoUtil = kakaoUtil;
        this.memberService = memberService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<Boolean> getMemberInfo(@RequestParam String token) throws JsonProcessingException {
        System.out.println("token = " + token);

        // 토큰 db 저장

        // 토큰을 사용해 프로필 정보 요청
        TokenResponse response = new TokenResponse("success");
//        String kakaoResponse = kakaoUtil.getUserInfo(token);
        KakaoResponse kakaoResponse = kakaoUtil.getUserInfo(token);

        System.out.println("kakaoUserInfo = " + kakaoResponse.toString());
        System.out.println("kakaoUserInfo = " + kakaoResponse.getKakaoAccount().toString());
        System.out.println("kakaoUserInfo = " + kakaoResponse.getProperties().toString());
        Member member = new Member(null,
                kakaoResponse.getKakaoAccount().getEmail(),
                kakaoResponse.getProperties().getNickname(),
                kakaoResponse.getKakaoAccount().getGender(),
                null);
//        System.out.println("kakaoUserInfo = " + kakaoResponse);
        // 프로필 db 저장
//        memberService.loginUser(member);
        return ResponseEntity.ok(true);
    }


}
