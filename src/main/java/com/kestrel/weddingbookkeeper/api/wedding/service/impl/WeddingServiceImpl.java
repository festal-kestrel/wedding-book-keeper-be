package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.GenderMatchException;
import com.kestrel.weddingbookkeeper.api.member.exception.InvalidRoleNameException;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFoundException;
import com.kestrel.weddingbookkeeper.api.member.exception.UnsupportedGenderTypeException;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingSaveDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInformationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.GuestDonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;
import com.kestrel.weddingbookkeeper.api.wedding.factory.WeddingStrategyFactory;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingStrategy;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.exception.InvalidGenderException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.ValidationCodeMisMatchException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotUpdateException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInformationUpdateException;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingServiceImpl implements WeddingService {

    private final MemberDao memberDao;
    private final WeddingDao weddingDao;
    private final MemberWeddingDao memberWeddingDao;
    private final MemberService memberService;
    private final WeddingStrategyFactory weddingStrategyFactory;

    public WeddingServiceImpl(final MemberDao memberDao,
                              final WeddingDao weddingDao,
                              final MemberWeddingDao memberWeddingDao,
                              final MemberService memberService,
                              final WeddingStrategyFactory weddingStrategyFactory) {
        this.memberDao = memberDao;
        this.weddingDao = weddingDao;
        this.memberWeddingDao = memberWeddingDao;
        this.memberService = memberService;
        this.weddingStrategyFactory = weddingStrategyFactory;
    }

    @Override
    @Transactional
    public Long saveWedding(final Member member, final WeddingInfoRequest weddingInfoRequest) {
        WeddingStrategy strategy = weddingStrategyFactory.getWeddingStrategy(member.getGender());
        return strategy.createWedding(member, weddingInfoRequest);
    }

    @Override
    public void updateQrImgUrl(final Long weddingId, final String qrImgUrl) {
        boolean isSaved = weddingDao.updateQrImgUrl(new WeddingUpdateDto(weddingId, qrImgUrl)) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotUpdateException();
        }
    }

    public WeddingInfoResponse selectWeddingInfo(Long weddingId) {
        Wedding wedding = weddingDao.selectWeddingInfo(weddingId);
        return new WeddingInfoResponse(wedding);
    }

    @Override
    @Transactional
    public void connectPartner(PartnerCodeRequest partnerCodeRequest, Long memberId) {
        Member member = memberDao.selectById(memberId).orElseThrow(MemberNotFoundException::new);
        Wedding wedding = weddingDao.selectByPartnerCode(partnerCodeRequest.getPartnerCode())
            .orElseThrow(ValidationCodeMisMatchException::new);

        switch (member.getGender()) {
            case MALE -> weddingDao.updateGroomPartner(new PartnerDto(wedding, member));
            case FEMALE -> weddingDao.updateBridePartner(new PartnerDto(wedding, member));
            default -> throw new InvalidGenderException();
        }
    }

    @Override
    @Transactional
    public void updateWeddingInformation(Long weddingId,
                                         WeddingUpdateInformationRequest weddingUpdateInformationRequest) {
        boolean isUpdate = weddingDao.updateWeddingInformation(
            new WeddingInfoUpdateDto(weddingId, weddingUpdateInformationRequest)) == 1;
        if (!isUpdate) {
            throw new WeddingInformationUpdateException();
        }
    }

    @Override
    public WeddingManagerCodeResponse selectManagerCode(Long weddingId) {
        Wedding wedding = weddingDao.selectManagerCode(weddingId);
        return new WeddingManagerCodeResponse(wedding);
    }

    public WeddingQrResponse selectQrImgUrl(Long weddingId) {
        Wedding wedding = weddingDao.selectWeddingInfo(weddingId);
        return new WeddingQrResponse(wedding);
    }

    public DonationReceiptsResponse selectDonationList(Long memberId) {
        List<MemberWedding> memberWeddings = memberWeddingDao.selectDonationList(memberId);
        List<DonationReceiptResponse> response = memberWeddings.stream()
            .map(DonationReceiptResponse::new).toList();
        return new DonationReceiptsResponse(response);
    }

    @Override
    public GuestDonationReceiptsResponse getWeddingGuestsInformation(Long weddingId, Role role) {
        Wedding wedding = weddingDao.selectWeddingInfo(weddingId);
        if (role == Role.MANAGER) {
            List<MemberWedding> memberWeddings = memberWeddingDao.selectGuestsByWeddingId(weddingId);
            return new GuestDonationReceiptsResponse(wedding, memberWeddings);
        }
        if (role == Role.PARTNER) {
            List<MemberWedding> memberWeddings = memberWeddingDao.selectGuestsByWeddingIdAndHasPaid(weddingId);
            return new GuestDonationReceiptsResponse(wedding, memberWeddings);
        }
        throw new InvalidRoleNameException();
    }

    @Override
    public Long registerPartner(final Member member, final Member partner) {
        assertGenderMismatch(member, partner);
        WeddingStrategy weddingStrategy = weddingStrategyFactory.getWeddingStrategy(partner.getGender());
        Wedding wedding = weddingStrategy.getWedding(partner);
        weddingStrategy.connectPartner(wedding, member);
        return wedding.getWeddingId();
    }

    private void assertGenderMismatch(Member member, Member partner) {
        if (member.getGender() == partner.getGender()) {
            throw new GenderMatchException();
        }
    }

    @Override
    @Transactional
    public void createMemberWeddingInfo(Long weddingId, Long memberId, MemberWeddingDto memberWeddingDto) {
        Member member = memberService.getMember(memberId);
        memberWeddingDao.insertMemberWedding(new MemberWeddingSaveDto(member, weddingId, memberWeddingDto));
    }

    @Override
    public WeddingIdResponse getWedding(Member member) {
        WeddingStrategy weddingStrategy = weddingStrategyFactory.getWeddingStrategy(member.getGender());
        Wedding wedding = weddingStrategy.getWedding(member);
        return new WeddingIdResponse(wedding.getWeddingId());
    }

    @Override
    @Transactional
    public void patchDonationApproval(Long weddingId, Long memberId) {
        memberWeddingDao.patchDonationApproval(weddingId, memberId);
    }

    @Override
    @Transactional
    public void patchDonationRejection(Long weddingId, Long memberId) {
        memberWeddingDao.patchDonationRejection(weddingId, memberId);
    }
}
