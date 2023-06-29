package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingServiceImpl implements WeddingService {

    private final WeddingDao weddingDao;

    public WeddingServiceImpl(final WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    @Override
    @Transactional
    public void saveWedding(Member member, final WeddingInfoRequestDto weddingInfoRequestDto, String qrImgUrl) {
        boolean isSaved = weddingDao.save(new WeddingInsertDto(member, weddingInfoRequestDto, qrImgUrl)) == 1;
        if (!isSaved) {
            throw new WeddingInfoNotSavedException();
        }
    }
}
