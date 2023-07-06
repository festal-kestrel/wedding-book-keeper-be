package com.kestrel.weddingbookkeeper.api.auth.dto.response;

import lombok.Getter;

@Getter
public class VerifyPartnerVerificationCodeResponse {

    private Long weddingId;

    public VerifyPartnerVerificationCodeResponse(Long weddingId) {
        this.weddingId = weddingId;
    }
}
