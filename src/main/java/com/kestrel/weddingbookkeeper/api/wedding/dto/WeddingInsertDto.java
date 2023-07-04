package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.utils.VerificationCodeGenerator;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeddingInsertDto {

    public Long weddingId;
    public Long groomId;
    public Long brideId;
    public String partnerCode;
    public String managerCode;
    public String groomName;
    public String brideName;
    public String location;
    public LocalDateTime weddingDate;

    public WeddingInsertDto(Member member, WeddingInfoRequest weddingInfoRequest) {
        this.groomId = member.getGender() == Gender.MALE ? member.getMemberId() : null;
        this.brideId = member.getGender() == Gender.FEMALE ? member.getMemberId() : null;
        this.partnerCode = VerificationCodeGenerator.generate();
        this.managerCode = VerificationCodeGenerator.generate();
        this.groomName = member.getGender() == Gender.MALE ? member.getName() : null;
        this.brideName = member.getGender() == Gender.FEMALE ? member.getName() : null;
        this.location = weddingInfoRequest.getLocation();
        this.weddingDate = weddingInfoRequest.getWeddingDate();
    }
}
