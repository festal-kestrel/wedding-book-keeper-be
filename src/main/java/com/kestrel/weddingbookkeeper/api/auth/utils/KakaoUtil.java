package com.kestrel.weddingbookkeeper.api.auth.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoUtil {

    public KakaoResponseDto getUserInfo(String kakaoToken) throws JsonProcessingException {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com/v2/user/me")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                .build();
        return webClient
                .post()
                .retrieve()
                .bodyToMono(KakaoResponseDto.class)
                .block();

    }
}
