package com.kestrel.weddingbookkeeper.api.member.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    private Long memberId;
    private String email;
    private String name;
    private Gender gender;
    private boolean isPartnerCodeIssued;

    public Member(Long memberId, String email, String name, Gender gender) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.gender = gender;
    }
}
