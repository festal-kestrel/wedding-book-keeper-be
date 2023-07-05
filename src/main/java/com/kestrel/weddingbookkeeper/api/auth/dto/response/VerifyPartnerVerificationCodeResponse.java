package com.kestrel.weddingbookkeeper.api.auth.dto.response;

public class VerifyPartnerVerificationCodeResponse {

    private Long weddingId;

    public VerifyPartnerVerificationCodeResponse(Long weddingId) {
        this.weddingId = weddingId;
    }
}
