package com.kestrel.weddingbookkeeper.api.member.facade;

import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;

public interface WeddingFacade {

    void createWeddingInfo(WeddingInfoRequestDto weddingInfoRequestDto);
}
