package com.kestrel.weddingbookkeeper.api.wedding.dto;

public class WeddingUpdateDto {

    private Integer weddingId;
    private String qrImgUrl;

    public WeddingUpdateDto(Integer weddingId, String qrImgUrl) {
        this.weddingId = weddingId;
        this.qrImgUrl = qrImgUrl;
    }

    public Integer getWeddingId() {
        return weddingId;
    }

    public String getQrImgUrl() {
        return qrImgUrl;
    }
}
