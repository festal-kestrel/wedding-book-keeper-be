package com.kestrel.weddingbookkeeper.api.auth.dto;

public class KakaoAccount {
    String nickname;
    String email;
    String gender;

    public KakaoAccount(String nickname, String email, String gender) {
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
    }



    @Override
    public String toString() {
        return "KakaoUserInfo{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
