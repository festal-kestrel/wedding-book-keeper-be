package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingNotFoundException;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingStrategy;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.springframework.stereotype.Component;

@Component
public class GroomWeddingStrategy implements WeddingStrategy {

    private final WeddingDao weddingDao;

    public GroomWeddingStrategy(final WeddingDao weddingDao) {
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
        return weddingDao.selectByGroomId(member.getMemberId()).orElseThrow(WeddingNotFoundException::new);
    }
}
