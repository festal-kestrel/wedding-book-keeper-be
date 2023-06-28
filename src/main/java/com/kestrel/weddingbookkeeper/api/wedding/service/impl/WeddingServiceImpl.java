package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFountException;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingServiceImpl implements WeddingService {

    private final MemberDao memberDao;

    public WeddingServiceImpl(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    @Transactional
    public void createWeddingInfo(final WeddingInfoRequestDto weddingInfoRequestDto) {
        Member member = memberDao.selectById(weddingInfoRequestDto.getMemberId());
        if (member == null) {
            throw new MemberNotFountException();
        }
    }
}
