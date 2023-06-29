package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponse;
import com.kestrel.weddingbookkeeper.api.auth.dto.TokenResponse;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthToken;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.utils.KakaoUtil;
//import java.net.http.HttpHeaders;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import org.apache.el.parser.Token;
import org.springframework.http.HttpStatus;
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
    private final AuthTokenProvider authTokenProvider;

    public AuthController(KakaoUtil kakaoUtil, MemberService memberService, AuthTokenProvider authTokenProvider) {
        this.kakaoUtil = kakaoUtil;
        this.memberService = memberService;
        this.authTokenProvider = authTokenProvider;
    }

    @PostMapping("/authorize")
    public ResponseEntity<String> getMemberInfo(@RequestParam String token) throws JsonProcessingException {
        System.out.println("token = " + token);

        // 토큰 db 저장

        // 토큰을 사용해 프로필 정보 요청
//        TokenResponse response = new TokenResponse("success");
        KakaoResponse kakaoResponse = kakaoUtil.getUserInfo(token);

        AuthToken jwtToken = authTokenProvider.createUserAppToken(Long.toString(kakaoResponse.getId()));
        System.out.println("jwtToken = " + jwtToken);

        System.out.println("kakaoUserInfo = " + kakaoResponse.toString());
        System.out.println("kakaoUserInfo = " + kakaoResponse.getKakaoAccount().toString());
        System.out.println("kakaoUserInfo = " + kakaoResponse.getProperties().toString());
        Member member = new Member(null,
                kakaoResponse.getKakaoAccount().getEmail(),
                kakaoResponse.getProperties().getNickname(),
                convertStringToGender(kakaoResponse.getKakaoAccount().getGender()));
        // 프로필 db 저장
        memberService.loginUser(member);
        return ResponseEntity.status(HttpStatus.OK).body(jwtToken.getToken());
//        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(jwtToken.getToken()));
    }

    private Gender convertStringToGender(String genderString) {
        if (genderString.equalsIgnoreCase("MALE")) {
            return Gender.MALE;
        } else if (genderString.equalsIgnoreCase("FEMALE")) {
            return Gender.FEMALE;
        }
        return null;
    }
}
