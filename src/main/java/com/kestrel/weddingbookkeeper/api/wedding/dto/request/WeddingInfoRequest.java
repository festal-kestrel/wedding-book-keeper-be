package com.kestrel.weddingbookkeeper.api.wedding.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class WeddingInfoRequest {

    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime weddingDate;

    public String getLocation() {
        return location;
    }

    public LocalDateTime getWeddingDate() {
        return weddingDate;
    }
}
