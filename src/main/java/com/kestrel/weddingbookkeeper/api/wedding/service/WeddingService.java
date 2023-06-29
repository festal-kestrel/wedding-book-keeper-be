package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.springframework.stereotype.Service;

@Service
public class WeddingService {
    private final WeddingDao weddingDao;

    public WeddingService(WeddingDao weddingDao) {
        this.weddingDao = weddingDao;
    }

    public Wedding getWeddingInfo(Integer weddingId) {
        return weddingDao.getWeddingInfo(weddingId);
    }
}
