package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.util.AuthorizationCodeGenerator;
import java.time.LocalDateTime;
import lombok.ToString;

@ToString
public class WeddingInsertDto {

    public Integer groomId;
    public Integer brideId;
    public String qrImgUrl;
    public String partnerCode;
    public String managerCode;
    public String groomName;
    public String brideName;
    public String location;
    public LocalDateTime weddingDate;

    public WeddingInsertDto(Member member, WeddingInfoRequestDto weddingInfoRequestDto, String qrImgUrl) {
        this.groomId = member.getGender() == Gender.MALE ? member.getId() : null;
        this.brideId = member.getGender() == Gender.FEMALE ? member.getId() : null;
        this.qrImgUrl = qrImgUrl;
        this.partnerCode = AuthorizationCodeGenerator.generateQrCode();
        this.managerCode = AuthorizationCodeGenerator.generateQrCode();
        this.groomName = member.getGender() == Gender.MALE ? member.getName() : null;
        this.brideName = member.getGender() == Gender.FEMALE ? member.getName() : null;
        this.location = weddingInfoRequestDto.getLocation();
        this.weddingDate = weddingInfoRequestDto.getWeddingDate();
    }
}
