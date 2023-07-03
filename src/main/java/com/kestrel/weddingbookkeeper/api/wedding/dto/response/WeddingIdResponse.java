package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import lombok.Getter;

@Getter
public class WeddingIdResponse {

    private Integer weddingId;

    public WeddingIdResponse(Integer weddingId) {
        this.weddingId = weddingId;
    }
}
