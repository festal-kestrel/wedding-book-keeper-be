package com.kestrel.weddingbookkeeper.api.wedding.dto;

public class PartnerDto {

    private Integer memberId;
    private String name;
    public PartnerDto(Integer memberId, String name) {

        this.memberId = memberId;
        this.name = name;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}
