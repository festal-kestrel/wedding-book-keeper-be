package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import java.time.LocalDateTime;

public class WeddingBrideInsertDto {

    private Long weddingId;
    private final Long brideId;
    private final String brideName;
    private final String location;
    private final LocalDateTime weddingDate;

    public WeddingBrideInsertDto(Long weddingId,
                                 Long brideId,
                                 String brideName,
                                 String location,
                                 LocalDateTime weddingDate) {
        this.weddingId = weddingId;
        this.brideId = brideId;
        this.brideName = brideName;
        this.location = location;
        this.weddingDate = weddingDate;
    }

    public WeddingBrideInsertDto(Member member, WeddingInfoRequest weddingInfoRequest) {
        this(null, member.getMemberId(), member.getName(), weddingInfoRequest.getLocation(),
                weddingInfoRequest.getWeddingDate());
    }

    public void setWeddingId(Long weddingId) {
        this.weddingId = weddingId;
    }

    public Long getWeddingId() {
        return weddingId;
    }
}
