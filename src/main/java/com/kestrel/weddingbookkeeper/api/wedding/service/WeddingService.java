package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;

public interface WeddingService {

    Integer saveWedding(Member member, WeddingInfoRequest weddingInfoRequest);

    void updateWeddingInfo(Integer weddingId, String qrImgUrl);
}
