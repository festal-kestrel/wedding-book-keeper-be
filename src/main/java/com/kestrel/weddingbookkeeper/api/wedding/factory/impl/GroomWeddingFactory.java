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
public class GroomWeddingFactory implements WeddingFactory {

    private final WeddingDao weddingDao;

    public GroomWeddingFactory(final WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    @Override
    public Long connectPartner(Member groom, Member bride) {
        Wedding wedding = weddingDao.selectByBrideId(bride.getMemberId()).orElseThrow(WeddingNotFoundException::new);
        weddingDao.updateGroomPartner(new PartnerDto(wedding, groom));
        return wedding.getWeddingId();
    }

    @Override
    public Wedding getWedding(Member member) {
        return weddingDao.selectByGroomId(member.getMemberId()).orElseThrow(WeddingNotFoundException::new);
    }

    @Override
    public boolean isSupport(Gender gender) {
        return Gender.MALE == gender;
    }
}
