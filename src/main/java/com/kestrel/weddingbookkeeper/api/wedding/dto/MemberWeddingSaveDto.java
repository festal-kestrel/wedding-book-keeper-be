package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;

public class MemberWeddingSaveDto {

    private Long memberId;
    private Long weddingId;
    private int donationAmount;
    private String relation;
    private Boolean isGroomSide;
    private String guestName;
    private String fcmToken;

    public MemberWeddingSaveDto(Member member, Long weddingId, MemberWeddingDto memberWeddingDto) {
        this.memberId = member.getMemberId();
        this.weddingId = weddingId;
        this.donationAmount = memberWeddingDto.getDonationAmount();
        this.relation = memberWeddingDto.getRelation();
        this.isGroomSide = memberWeddingDto.getIsGroomSide();
        this.guestName = member.getName();
        this.fcmToken = memberWeddingDto.getFcmToken();
    }
}
