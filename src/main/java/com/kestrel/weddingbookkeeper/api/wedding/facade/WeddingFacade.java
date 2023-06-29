package com.kestrel.weddingbookkeeper.api.wedding.facade;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;

public interface WeddingFacade {

    void createWeddingInfo(Integer memberId, WeddingInfoRequest weddingInfoRequest);
}
