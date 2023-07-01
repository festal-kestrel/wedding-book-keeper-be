package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WeddingInfoResponse {

    private String groomName;
    private String brideName;
    private LocalDateTime weddingDate;
    private String location;

    public WeddingInfoResponse(Wedding wedding) {
        this.groomName = wedding.getGroomName();
        this.brideName = wedding.getBrideName();
        this.weddingDate = wedding.getWeddingDate();
        this.location = wedding.getLocation();
    }
}
