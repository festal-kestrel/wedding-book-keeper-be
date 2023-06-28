package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.common.util.DateUtil;
import java.time.LocalDateTime;

public class WeddingInfoRequestDto {

    private Integer memberId;
    private String location;
    private LocalDateTime weddingDate;

    public WeddingInfoRequestDto(Integer memberId, String location, String weddingDate) {
        this.memberId = memberId;
        this.location = location;
        this.weddingDate = DateUtil.getLocalDateTime(weddingDate);
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
