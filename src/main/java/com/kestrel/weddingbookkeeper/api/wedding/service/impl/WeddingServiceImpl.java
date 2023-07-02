package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFoundException;
import com.kestrel.weddingbookkeeper.api.member.exception.UnsupportedGenderTypeException;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.factory.WeddingFactory;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInfomationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.exception.InvalidGenderException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.ValidationCodeMisMatchException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotUpdateException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfomationNotUpdateException;
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
    public void updateWeddingInfo(final Integer weddingId, final String qrImgUrl) {
        boolean isSaved = weddingDao.updateQrImgUrl(new WeddingUpdateDto(weddingId, qrImgUrl)) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotUpdateException();
        }
    }

    public WeddingInfoResponse selectWeddingInfo(Integer weddingId) {

        Wedding wedding = weddingDao.selectWeddingInfo(weddingId);
        WeddingInfoResponse weddingInfoResponse = new WeddingInfoResponse(wedding);
        return weddingInfoResponse;
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
    public void updateWeddingInfomation(Integer weddingId, WeddingUpdateInfomationRequest weddingUpdateInfomationRequest) {
        boolean isUpdate = weddingDao.updateWeddingInfomation(
                new WeddingInfoUpdateDto(weddingId, weddingUpdateInfomationRequest)) == 1;
        if (!isUpdate) {
            throw new WeddingInfomationNotUpdateException();
        }
    }

    @Override
    public WeddingManagerCodeResponse selectManagerCode(Integer weddingId) {
        Wedding wedding = weddingDao.selectManagerCode(weddingId);
        WeddingManagerCodeResponse weddingManagerCodeResponse = new WeddingManagerCodeResponse(wedding);
        return weddingManagerCodeResponse;
    }

    public WeddingQrResponse selectQrImgUrl(Integer weddingId) {
        Wedding wedding = weddingDao.selectQrImgUrl(weddingId);
        WeddingQrResponse weddingQrResponse = new WeddingQrResponse(wedding);
        return weddingQrResponse;

    }

    public List<MemberWedding> selectDonationList(Integer memberId) {
        List<MemberWedding> list = memberWeddingDao.selectDonationList(memberId);
        return list;
    }

    @Override
    public List<MemberWedding> selectGuestList(Integer weddingId, Boolean hasPaid) {
        if (hasPaid == null) {
            List<MemberWedding> list = memberWeddingDao.selectGuestListByAdmin(weddingId);
            return list;
        }
        if (hasPaid) {
            List<MemberWedding> list = memberWeddingDao.selectGuestListByCouple(weddingId);
            return list;
        }
        return null;
    }

    @Override
    public void registerPartner(Member member, Member partner) {
        WeddingFactory weddingFactory = getWeddingFactory(member);
        weddingFactory.connectPartner(member, partner);
    }

    private WeddingFactory getWeddingFactory(final Member member) {
        return weddingFactories.stream()
                .filter(weddingFactory -> weddingFactory.isSupport(member.getGender()))
                .findFirst()
                .orElseThrow(UnsupportedGenderTypeException::new);
    }
}
