package com.kestrel.weddingbookkeeper.api.member.service.impl;

import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFoundException;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotRegisteredException;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    public MemberServiceImpl(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Member getMember(Integer memberId) {
        return memberDao.selectById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public void registerNewMember(Member member) {
        boolean isRegistered = memberDao.insertMember(member) == 1;
        if (!isRegistered) {
            throw new MemberNotRegisteredException();
        }
    }

    public boolean isNewUser(Member member) {
        Optional<Member> optionalMember = memberDao.selectByEmail(member);
        return optionalMember.isEmpty();
    }
}
