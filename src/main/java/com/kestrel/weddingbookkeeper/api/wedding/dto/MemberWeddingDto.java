package com.kestrel.weddingbookkeeper.api.wedding.dto;

import lombok.Data;

@Data
public class MemberWeddingDto {

    private Long weddingId;
    private int donationAmount;
    private Boolean hasPaid;
    private String relation;
    private Boolean isGroomSide;
    private String fcmToken;
}
