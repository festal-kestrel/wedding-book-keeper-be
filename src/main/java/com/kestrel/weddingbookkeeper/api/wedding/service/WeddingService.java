package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;

public interface WeddingService {

    void saveWedding(Member member, WeddingInfoRequestDto weddingInfoRequestDto, String qrImgUrl);
}
