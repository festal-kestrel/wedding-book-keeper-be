package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.GuestDonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInformationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.ManagerVerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;

public interface WeddingService {

    Long saveWedding(Member member, WeddingInfoRequest weddingInfoRequest);

    void updateQrImgUrl(Long weddingId, String qrImgUrl);

    WeddingInfoResponse selectWeddingInfo(Long weddingId);

    DonationReceiptsResponse selectDonationList(Long memberId);

    void connectPartner(PartnerCodeRequest partnerCodeRequest, Long memberId);

    void updateWeddingInformation(Long weddingId, WeddingUpdateInformationRequest weddingUpdateinformationRequest);

    WeddingManagerCodeResponse selectManagerCode(Long weddingId);

    WeddingQrResponse selectQrImgUrl(Long weddingId);

    GuestDonationReceiptsResponse getWeddingGuestsInformation(Long weddingId, Role role);

    void registerPartner(Member member, Member partner);

    void createMemberWeddingInfo(Long weddingId, Long memberId, MemberWeddingDto memberWeddingDto);

    WeddingIdResponse getWedding(Member member);

    void patchDonationApproval(Long weddingId, Long memberId);

    void patchDonationRejection(Long weddingId, Long memberId);

    VerificationCodeResponse getManagerVerificationCode(Long weddingId);
}
