package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInfomationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import java.util.List;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.time.LocalDateTime;

public interface WeddingService {

    Integer saveWedding(Member member, WeddingInfoRequest weddingInfoRequest);

    void updateWeddingInfo(Integer weddingId, String qrImgUrl);

    WeddingInfoResponse selectWeddingInfo(Integer weddingId);

    boolean connectPartner(PartnerCodeRequest partnerCodeRequest, Integer memberId);

    List<MemberWedding> selectDonationList(Integer memberId);

    void connectPartner(PartnerCodeRequest partnerCodeRequest, Integer memberId);

    void updateWeddingInfomation(Integer weddingId, WeddingUpdateInfomationRequest weddingUpdateInfomationRequest);

    WeddingQrResponse selectQrImgUrl(Integer weddingId);

}
