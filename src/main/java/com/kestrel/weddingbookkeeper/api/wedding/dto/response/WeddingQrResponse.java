package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import lombok.Getter;

@Getter
public class WeddingQrResponse {

    private String qrImgUrl;

    public WeddingQrResponse(Wedding wedding) {
        this.qrImgUrl = wedding.getQrImgUrl();
    }
}
