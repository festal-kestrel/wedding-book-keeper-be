package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import java.time.LocalDateTime;

public class DetailResponse {

    private final LocalDateTime weddingDate;
    private final String groomName;
    private final String brideName;
    private final String location;

    public DetailResponse(LocalDateTime weddingDate, String groomName, String brideName, String location) {
        this.weddingDate = weddingDate;
        this.groomName = groomName;
        this.brideName = brideName;
        this.location = location;
    }

    public LocalDateTime getWeddingDate() {
        return weddingDate;
    }

    public String getGroomName() {
        return groomName;
    }

    public String getBrideName() {
        return brideName;
    }

    public String getLocation() {
        return location;
    }
}
