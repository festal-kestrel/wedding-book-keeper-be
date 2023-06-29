package com.kestrel.weddingbookkeeper.api.memberWedding.service.impl;

import com.kestrel.weddingbookkeeper.api.memberWedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.memberWedding.service.MemberWeddingService;
import com.kestrel.weddingbookkeeper.api.memberWedding.vo.MemberWedding;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberWeddingServiceImpl implements MemberWeddingService {

    private final MemberWeddingDao memberWeddingDao;

    public MemberWeddingServiceImpl(final MemberWeddingDao memberWeddingDao) {
        this.memberWeddingDao = memberWeddingDao;
    }

    @Override
    public List<MemberWedding> selectDonationList(Integer memberId) {
        List<MemberWedding> list = memberWeddingDao.selectDonationList(memberId);
        return list;
    }
}
