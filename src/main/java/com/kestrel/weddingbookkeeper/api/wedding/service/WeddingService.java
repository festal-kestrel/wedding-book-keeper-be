package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;

public interface WeddingService {
    Wedding getWeddingInfo(Integer weddingId);
}
