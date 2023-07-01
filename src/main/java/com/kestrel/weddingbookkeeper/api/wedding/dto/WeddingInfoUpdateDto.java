package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInfomationRequest;
import java.time.LocalDateTime;

public class WeddingInfoUpdateDto {

    private Integer weddingId;
    private LocalDateTime weddingDate;
    private String location;

    public WeddingInfoUpdateDto(Integer weddingId, WeddingUpdateInfomationRequest weddingUpdateInfomationRequest) {
        this.weddingId = weddingId;
        this.weddingDate = weddingUpdateInfomationRequest.getWeddingDate();
        this.location = weddingUpdateInfomationRequest.getLocation();
    }

    public Integer getWeddingId() {
        return weddingId;
    }

    public LocalDateTime getWeddingDate() {
        return weddingDate;
    }

    public String getLocation() {
        return location;
    }
}
