package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotSavedException;
import com.kestrel.weddingbookkeeper.api.wedding.exception.WeddingInfoNotUpdateException;
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
    public Integer saveWedding(Member member, final WeddingInfoRequest weddingInfoRequest) {
        WeddingInsertDto weddingInsertDto = new WeddingInsertDto(member, weddingInfoRequest);
        boolean isSaved = weddingDao.save(weddingInsertDto) == 1;
        System.out.println("member = " + member);
        System.out.println("weddingInsertDto = " + weddingInsertDto);
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
}
