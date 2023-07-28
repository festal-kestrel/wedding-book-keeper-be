package com.kestrel.weddingbookkeeper.api.wedding.factory.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingBrideInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.factory.WeddingFactory;
import org.springframework.stereotype.Component;

@Component
public class BrideWeddingFactory implements WeddingFactory {

    private final WeddingDao weddingDao;

    public BrideWeddingFactory(final WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
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

    @Override
    public boolean isSupport(Gender gender) {
        return Gender.FEMALE == gender;
    }
}
