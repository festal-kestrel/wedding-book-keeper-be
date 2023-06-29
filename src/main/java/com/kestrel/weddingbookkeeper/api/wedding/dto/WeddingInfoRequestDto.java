package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.common.util.DateUtils;
import java.time.LocalDateTime;

public class WeddingInfoRequestDto {

    private Integer memberId;
    private String location;
    private LocalDateTime weddingDate;

    public WeddingInfoRequestDto(Integer memberId, WeddingInfoRequest weddingInfoRequest) {
        this.memberId = memberId;
        this.location = weddingInfoRequest.getLocation();
        this.weddingDate = DateUtils.getLocalDateTime(weddingInfoRequest.getWeddingDate());
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getWeddingDate() {
        return weddingDate;
    }
}
