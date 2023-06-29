package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import lombok.Data;

@Data
public class PartnerDto {

    private Integer weddingId;
    private Integer groomId;
    private String groomName;
    private Integer brideId;
    private String brideName;

    public PartnerDto(Wedding wedding, Member member) {

        this.weddingId = wedding.getWeddingId();
        this.groomId = member.getGender() == Gender.MALE ? member.getMemberId() : null;
        this.groomName = member.getGender() == Gender.MALE ? member.getName() : null;
        this.brideId = member.getGender() == Gender.FEMALE ? member.getMemberId() : null;
        this.brideName = member.getGender() == Gender.FEMALE ? member.getName() : null;
    }
}
