package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;

public interface WeddingStrategy {

    void connectPartner(Wedding wedding, Member member);

    Wedding getWedding(Member member);

    Long createWedding(Member member, WeddingInfoRequest weddingInfoRequest);
}
