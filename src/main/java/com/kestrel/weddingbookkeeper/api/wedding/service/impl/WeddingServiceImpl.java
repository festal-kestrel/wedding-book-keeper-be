package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.InvalidRoleNameException;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFoundException;
import com.kestrel.weddingbookkeeper.api.member.exception.UnsupportedGenderTypeException;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInformationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.GuestDonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;
import com.kestrel.weddingbookkeeper.api.wedding.factory.WeddingFactory;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.exception.InvalidGenderException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.ValidationCodeMisMatchException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
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
    private final List<WeddingFactory> weddingFactories;

    public WeddingServiceImpl(final MemberDao memberDao,
                              final WeddingDao weddingDao,
                              final MemberWeddingDao memberWeddingDao,
                              final List<WeddingFactory> weddingFactories) {
        this.memberDao = memberDao;
        this.weddingDao = weddingDao;
        this.memberWeddingDao = memberWeddingDao;
        this.weddingFactories = weddingFactories;
    }

    @Override
    @Transactional
    public Integer saveWedding(Member member, final WeddingInfoRequest weddingInfoRequest) {
        WeddingInsertDto weddingInsertDto = new WeddingInsertDto(member, weddingInfoRequest);
        boolean isSaved = weddingDao.save(weddingInsertDto) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotSavedException();
        }
        return weddingInsertDto.getWeddingId();
    }

    @Override
    public void updateQrImgUrl(final Integer weddingId, final String qrImgUrl) {
        boolean isSaved = weddingDao.updateQrImgUrl(new WeddingUpdateDto(weddingId, qrImgUrl)) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotUpdateException();
        }
    }

    public WeddingInfoResponse selectWeddingInfo(Integer weddingId) {
        Wedding wedding = weddingDao.selectWeddingInfo(weddingId);
        return new WeddingInfoResponse(wedding);
    }

    @Override
    @Transactional
    public void connectPartner(PartnerCodeRequest partnerCodeRequest, Integer memberId) {
        Member member = memberDao.selectById(memberId).orElseThrow(MemberNotFoundException::new);
        Wedding wedding = weddingDao.selectByPartnerCode(partnerCodeRequest.getPartnerCode());

        if (wedding == null) {
            throw new ValidationCodeMisMatchException();
        }
        switch (member.getGender()) {
            case MALE -> weddingDao.updateGroomPartner(new PartnerDto(wedding, member));
            case FEMALE -> weddingDao.updateBridePartner(new PartnerDto(wedding, member));
            default -> throw new InvalidGenderException();
        }
    }

    @Override
    @Transactional
    public void updateWeddinginformation(Integer weddingId, WeddingUpdateInformationRequest weddingUpdateInformationRequest) {
        boolean isUpdate = weddingDao.updateWeddingInformation(
                new WeddingInfoUpdateDto(weddingId, weddingUpdateInformationRequest)) == 1;
        if (!isUpdate) {
            throw new WeddingInformationUpdateException();
        }
    }

    @Override
    public WeddingManagerCodeResponse selectManagerCode(Integer weddingId) {
        Wedding wedding = weddingDao.selectManagerCode(weddingId);
        WeddingManagerCodeResponse weddingManagerCodeResponse = new WeddingManagerCodeResponse(wedding);
        return weddingManagerCodeResponse;
    }

    public WeddingQrResponse selectQrImgUrl(Integer weddingId) {
        Wedding wedding = weddingDao.selectWeddingInfo(weddingId);
        return new WeddingQrResponse(wedding);
    }

    public DonationReceiptsResponse selectDonationList(Integer memberId) {
        List<MemberWedding> memberWeddings = memberWeddingDao.selectDonationList(memberId);
        List<DonationReceiptResponse> response = memberWeddings.stream()
                .map(DonationReceiptResponse::new).toList();
        return new DonationReceiptsResponse(response);
    }

    @Override
    public GuestDonationReceiptsResponse getWeddingGuestsInformation(Integer weddingId, Role role) {
        if (role == Role.MANAGER) {
            List<MemberWedding> memberWeddings = memberWeddingDao.selectGuestsByWeddingId(weddingId);
            return new GuestDonationReceiptsResponse(memberWeddings);
        }
        if (role == Role.PARTNER) {
            List<MemberWedding> memberWeddings = memberWeddingDao.selectGuestsByWeddingIdAndHasPaid(weddingId);
            return new GuestDonationReceiptsResponse(memberWeddings);
        }
        throw new InvalidRoleNameException();
    }

    @Override
    public void registerPartner(Member member, Member partner) {
        WeddingFactory weddingFactory = getWeddingFactory(member);
        weddingFactory.connectPartner(member, partner);
    }

    @Override
    public WeddingIdResponse getWedding(Member member) {
        WeddingFactory weddingFactory = getWeddingFactory(member);
        Wedding wedding = weddingFactory.getWedding(member);
        return new WeddingIdResponse(wedding.getWeddingId());
    }

    private WeddingFactory getWeddingFactory(final Member member) {
        return weddingFactories.stream()
                .filter(weddingFactory -> weddingFactory.isSupport(member.getGender()))
                .findFirst()
                .orElseThrow(UnsupportedGenderTypeException::new);
    }
}
