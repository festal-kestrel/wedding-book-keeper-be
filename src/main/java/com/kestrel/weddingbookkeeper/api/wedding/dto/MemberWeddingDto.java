package com.kestrel.weddingbookkeeper.api.wedding.dto;

import lombok.Data;

@Data
public class MemberWeddingDto {
    private Integer weddingId;
    private Integer donationAmount;
    private Boolean hasPaid;
    private String relation;
    private Boolean isGroomSide;
}
