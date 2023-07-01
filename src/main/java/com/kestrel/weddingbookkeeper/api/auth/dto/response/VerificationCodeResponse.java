package com.kestrel.weddingbookkeeper.api.auth.dto.response;

public class VerificationCodeResponse {

    private String verificationCode;

    public VerificationCodeResponse(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
