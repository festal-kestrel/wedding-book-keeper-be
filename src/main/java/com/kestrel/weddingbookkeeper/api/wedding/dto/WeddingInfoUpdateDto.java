package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInformationRequest;
import java.time.LocalDateTime;

public class WeddingInfoUpdateDto {

    private Long weddingId;
    private LocalDateTime weddingDate;
    private String location;

    public WeddingInfoUpdateDto(Long weddingId, WeddingUpdateInformationRequest weddingUpdateinformationRequest) {
        this.weddingId = weddingId;
        this.weddingDate = weddingUpdateinformationRequest.getWeddingDate();
        this.location = weddingUpdateinformationRequest.getLocation();
    }

    public Long getWeddingId() {
        return weddingId;
    }

    public LocalDateTime getWeddingDate() {
        return weddingDate;
    }

    public String getLocation() {
        return location;
    }
}
