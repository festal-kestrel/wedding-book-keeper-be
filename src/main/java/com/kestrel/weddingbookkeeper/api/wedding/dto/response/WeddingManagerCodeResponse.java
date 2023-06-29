package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import lombok.Getter;

@Getter
public class WeddingManagerCodeResponse {

    private String managerCode;

    public WeddingManagerCodeResponse(Wedding wedding) {
        this.managerCode = wedding.getManagerCode();
    }
}
