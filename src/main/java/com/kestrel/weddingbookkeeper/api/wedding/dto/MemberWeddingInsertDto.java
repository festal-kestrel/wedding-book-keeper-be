package com.kestrel.weddingbookkeeper.api.wedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWeddingInsertDto {

    private Integer memberId;
    private Integer weddingId;
    private Integer donationAmount;
    private Integer hasPaid;
    private String relation;
    private Integer isGroomSide;
    private String guestName;
}
