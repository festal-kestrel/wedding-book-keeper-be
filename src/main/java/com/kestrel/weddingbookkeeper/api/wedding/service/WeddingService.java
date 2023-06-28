package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;

public interface WeddingService {

    void createWeddingInfo(WeddingInfoRequestDto weddingInfoRequestDto);
}
