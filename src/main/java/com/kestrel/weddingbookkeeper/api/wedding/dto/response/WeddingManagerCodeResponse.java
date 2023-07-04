package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import lombok.Getter;

@Getter
public class WeddingManagerCodeResponse {

    private String verificationCode;

    public WeddingManagerCodeResponse(Wedding wedding) {
        this.verificationCode = wedding.getManagerCode();
    }
}
