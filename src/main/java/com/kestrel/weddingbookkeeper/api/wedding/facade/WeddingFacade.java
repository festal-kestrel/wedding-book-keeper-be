package com.kestrel.weddingbookkeeper.api.wedding.facade;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;

public interface WeddingFacade {

    void createWeddingInfo(Long memberId, WeddingInfoRequest weddingInfoRequest);

    WeddingIdResponse getWeddingId(Long memberId);
}
