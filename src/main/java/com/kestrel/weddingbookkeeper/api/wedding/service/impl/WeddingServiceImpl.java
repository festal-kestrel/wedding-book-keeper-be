package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.springframework.stereotype.Service;

@Service
public class WeddingServiceImpl implements WeddingService {
    private final WeddingDao weddingDao;

    public WeddingServiceImpl(WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    @Override
    public Wedding getWeddingInfo(Integer weddingId) {
        return weddingDao.getWeddingInfo(weddingId);
    }
}
