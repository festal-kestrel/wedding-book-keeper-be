package com.kestrel.weddingbookkeeper.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoResponseDto {

    private long id;
    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;


    public static class Properties {

        @JsonProperty("nickname")
        private String nickname;

        public String getNickname() {
            return nickname;
        }

    }

    public static class KakaoAccount {

        @JsonProperty("email")
        private String email;

        @JsonProperty("gender")
        private String gender;

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }

    }

    public long getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

}