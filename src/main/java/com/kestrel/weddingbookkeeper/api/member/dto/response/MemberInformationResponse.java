package com.kestrel.weddingbookkeeper.api.member.dto.response;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import lombok.Getter;

@Getter
public class MemberInformationResponse {

    private Integer memberId;
    private String email;
    private String name;
    private Gender gender;

    public MemberInformationResponse(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.gender = member.getGender();
    }
}
