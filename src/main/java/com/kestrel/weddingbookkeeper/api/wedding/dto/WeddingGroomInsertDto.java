package com.kestrel.weddingbookkeeper.api.wedding.dto;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import java.time.LocalDateTime;

public class WeddingGroomInsertDto {

    private Long weddingId;
    private final Long groomId;
    private final String groomName;
    private final String location;
    private final LocalDateTime weddingDate;

    public WeddingGroomInsertDto(Long weddingId,
                                 Long groomId,
                                 String groomName,
                                 String location,
                                 LocalDateTime weddingDate) {
        this.weddingId = weddingId;
        this.groomId = groomId;
        this.groomName = groomName;
        this.location = location;
        this.weddingDate = weddingDate;
    }

    public WeddingGroomInsertDto(Member member, WeddingInfoRequest weddingInfoRequest) {
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
