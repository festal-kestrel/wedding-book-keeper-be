package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInfomationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import java.util.List;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;

public interface WeddingService {

    Integer saveWedding(Member member, WeddingInfoRequest weddingInfoRequest);

    void updateQrImgUrl(Integer weddingId, String qrImgUrl);

    WeddingInfoResponse selectWeddingInfo(Integer weddingId);

    DonationReceiptsResponse selectDonationList(Integer memberId);

    void connectPartner(PartnerCodeRequest partnerCodeRequest, Integer memberId);

    void updateWeddingInfomation(Integer weddingId, WeddingUpdateInfomationRequest weddingUpdateInfomationRequest);

    WeddingManagerCodeResponse selectManagerCode(Integer weddingId);

    WeddingQrResponse selectQrImgUrl(Integer weddingId);

    List<MemberWedding> selectGuestList(Integer weddingId, Boolean hasPaid);

    void registerPartner(Member member, Member partner);
}
