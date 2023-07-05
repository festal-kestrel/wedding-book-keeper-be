package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

public class ManagerVerificationCodeResponse {

    private Long weddingId;

    public ManagerVerificationCodeResponse(Long weddingId) {
        this.weddingId = weddingId;
    }

    public Long getWeddingId() {
        return weddingId;
    }
}
