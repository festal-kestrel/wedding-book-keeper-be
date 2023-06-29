package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInfomationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import java.time.LocalDateTime;

public interface WeddingService {

    Integer saveWedding(Member member, WeddingInfoRequest weddingInfoRequest);

    void updateWeddingInfo(Integer weddingId, String qrImgUrl);

    WeddingInfoResponse selectWeddingInfo(Integer weddingId);

    void connectPartner(PartnerCodeRequest partnerCodeRequest, Integer memberId);

    void updateWeddingInfomation(Integer weddingId, WeddingUpdateInfomationRequest weddingUpdateInfomationRequest);
}
