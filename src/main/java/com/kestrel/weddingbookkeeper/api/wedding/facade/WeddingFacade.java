package com.kestrel.weddingbookkeeper.api.wedding.facade;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;

public interface WeddingFacade {

    void createWeddingInfo(Integer memberId, WeddingInfoRequest weddingInfoRequest);

    WeddingIdResponse getWeddingId(Integer memberId);
}
