package com.kestrel.weddingbookkeeper.api.wedding.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWedding extends BaseEntity {

    private Wedding wedding;
    private Member member;
    private Integer donationAmount;
    private boolean hasPaid;
    private String relation;
    private boolean isGroomSide;
    private Member memberId;
    private Wedding weddingId;
}
