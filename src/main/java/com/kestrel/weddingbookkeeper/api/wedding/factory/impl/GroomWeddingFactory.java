package com.kestrel.weddingbookkeeper.api.wedding.factory.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingGroomInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.factory.WeddingFactory;
import org.springframework.stereotype.Component;

@Component
public class GroomWeddingFactory implements WeddingFactory {

    private final WeddingDao weddingDao;

    public GroomWeddingFactory(final WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    @Override
    public Long createWedding(final Member member, final WeddingInfoRequest weddingInfoRequest) {
        WeddingGroomInsertDto weddingInsertDto = new WeddingGroomInsertDto(member, weddingInfoRequest);
        boolean isSaved = weddingDao.saveByGroom(weddingInsertDto) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotSavedException();
        }
        return weddingInsertDto.getWeddingId();
    }

    @Override
    public boolean isSupport(Gender gender) {
        return Gender.MALE == gender;
    }
}
