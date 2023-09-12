package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingBrideInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingNotFoundException;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingStrategy;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.springframework.stereotype.Component;

@Component
public class BrideWeddingStrategy implements WeddingStrategy {

    private final WeddingDao weddingDao;

    public BrideWeddingStrategy(final WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    @Override
    public void connectPartner(Wedding wedding, Member member) {
        boolean isSaved = weddingDao.updateBridePartner(new PartnerDto(wedding, member)) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotSavedException();
        }
    }

    @Override
    public Wedding getWedding(Member member) {
        return weddingDao.selectByBrideId(member.getMemberId()).orElseThrow(WeddingNotFoundException::new);
    }

    @Override
    public Long createWedding(final Member member, final WeddingInfoRequest weddingInfoRequest) {
        WeddingBrideInsertDto weddingBrideInsertDto = new WeddingBrideInsertDto(member, weddingInfoRequest);
        boolean isSaved = weddingDao.saveByBride(weddingBrideInsertDto) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotSavedException();
        }
        return weddingBrideInsertDto.getWeddingId();
    }
}
