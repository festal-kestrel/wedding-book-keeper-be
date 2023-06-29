package com.kestrel.weddingbookkeeper.api.wedding.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWedding extends BaseEntity {

    private int weddingId;
    private String memberId;
    private int donationAmount;
    private boolean hasPaid;
    private String relation;
    private int guestSide;
}
