package com.kestrel.weddingbookkeeper.api.member.service.impl;

import com.kestrel.weddingbookkeeper.api.auth.dto.KakaoResponseDto;
import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFoundException;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotRegisteredException;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
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
    public int loginAndGetMemberId(Optional<Member> memberOptional, KakaoResponseDto kakaoResponseDto) {
        int memberId;
        if (memberOptional.isEmpty()) {
            Member member = createMember(kakaoResponseDto);
            boolean isSaved = memberDao.insertMember(member) == 1;
            if (!isSaved) {
                throw new MemberNotRegisteredException();
            }
            memberId = member.getMemberId();
        } else {
            memberId = memberOptional.get().getMemberId();
        }
        return memberId;
    }

    @Override
    public Optional<Member> findCurrentUser(KakaoResponseDto kakaoResponseDto) {
        Member member = createMember(kakaoResponseDto);
        Optional<Member> memberOptional = memberDao.selectByEmail(member);
        return memberOptional;
    }

    private Member createMember(KakaoResponseDto kakaoResponseDto) {
        return new Member(null,
                kakaoResponseDto.getKakaoAccount().getEmail(),
                kakaoResponseDto.getProperties().getNickname(),
                convertStringToGender(kakaoResponseDto.getKakaoAccount().getGender()));
    }

    private Gender convertStringToGender(String genderString) {
        if (genderString.equalsIgnoreCase("MALE")) {
            return Gender.MALE;
        } else if (genderString.equalsIgnoreCase("FEMALE")) {
            return Gender.FEMALE;
        }
        return null;
    }
}
