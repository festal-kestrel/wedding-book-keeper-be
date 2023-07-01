package com.kestrel.weddingbookkeeper.api.wedding.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wedding extends BaseEntity {

    private Integer weddingId;
    private Member groom;
    private Member bride;
    private String qrImgUrl;
    private String partnerCode;
    private String managerCode;
    private String location;
    private LocalDateTime weddingDate;
    private String groomName;
    private String brideName;
    private boolean isManagerCodeIssued;
}
