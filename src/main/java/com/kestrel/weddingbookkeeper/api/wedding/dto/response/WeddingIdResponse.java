package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import lombok.Getter;

@Getter
public class WeddingIdResponse {

    private Long weddingId;

    public WeddingIdResponse(Long weddingId) {
        this.weddingId = weddingId;
    }
}
