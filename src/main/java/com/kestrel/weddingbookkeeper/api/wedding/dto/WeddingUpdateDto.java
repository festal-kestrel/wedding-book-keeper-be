package com.kestrel.weddingbookkeeper.api.wedding.dto;

public class WeddingUpdateDto {

    private Long weddingId;
    private String qrImgUrl;

    public WeddingUpdateDto(Long weddingId, String qrImgUrl) {
        this.weddingId = weddingId;
        this.qrImgUrl = qrImgUrl;
    }

    public Long getWeddingId() {
        return weddingId;
    }

    public String getQrImgUrl() {
        return qrImgUrl;
    }
}
