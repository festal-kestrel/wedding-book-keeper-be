package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kestrel.weddingbookkeeper.api.auth.service.JwtTokenService;
import com.kestrel.weddingbookkeeper.api.auth.vo.JwtToken;
import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponse;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthToken;
import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.utils.KakaoUtil;
//import java.net.http.HttpHeaders;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
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
    private final JwtTokenService jwtTokenService;
    private final AuthTokenProvider authTokenProvider;

    public AuthController(KakaoUtil kakaoUtil, MemberService memberService, JwtTokenService jwtTokenService, AuthTokenProvider authTokenProvider) {
        this.kakaoUtil = kakaoUtil;
        this.memberService = memberService;
        this.jwtTokenService = jwtTokenService;
        this.authTokenProvider = authTokenProvider;
    }

    @PostMapping("/authorize")
    public ResponseEntity<JwtToken> getMemberInfo(@RequestParam String token) throws JsonProcessingException {
        System.out.println("token = " + token);

        KakaoResponse kakaoResponse = kakaoUtil.getUserInfo(token);

        AuthToken authToken = authTokenProvider.createUserAppToken(Long.toString(kakaoResponse.getId()));
        JwtToken jwtToken = new JwtToken(authToken.getToken());
        Member member = new Member(null,
                kakaoResponse.getKakaoAccount().getEmail(),
                kakaoResponse.getProperties().getNickname(),
                convertStringToGender(kakaoResponse.getKakaoAccount().getGender()));
        if (memberService.isNewUser(member)){
            memberService.registerNewMember(member);
        }
        jwtTokenService.saveJwtToken(jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
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
