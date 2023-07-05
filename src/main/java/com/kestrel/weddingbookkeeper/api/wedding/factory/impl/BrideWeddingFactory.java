package com.kestrel.weddingbookkeeper.api.wedding.factory.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingNotFoundException;
import com.kestrel.weddingbookkeeper.api.wedding.factory.WeddingFactory;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.springframework.stereotype.Component;

@Component
public class BrideWeddingFactory implements WeddingFactory {

    private final WeddingDao weddingDao;

    public BrideWeddingFactory(final WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    @Override
    public Long connectPartner(Member bride, Member groom) {
        Wedding wedding = weddingDao.selectByGroomId(groom.getMemberId()).orElseThrow(WeddingNotFoundException::new);
        weddingDao.updateBridePartner(new PartnerDto(wedding, bride));
        return wedding.getWeddingId();
    }

    @Override
    public Wedding getWedding(Member member) {
        return weddingDao.selectByBrideId(member.getMemberId()).orElseThrow(WeddingNotFoundException::new);
    }

    @Override
    public boolean isSupport(Gender gender) {
        return Gender.FEMALE == gender;
    }
}
