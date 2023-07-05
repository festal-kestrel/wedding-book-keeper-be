package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

public class CreateWeddingResponse {

    private Long weddingId;

    public CreateWeddingResponse(Long weddingId) {
        this.weddingId = weddingId;
    }

    public Long getWeddingId() {
        return weddingId;
    }
}
