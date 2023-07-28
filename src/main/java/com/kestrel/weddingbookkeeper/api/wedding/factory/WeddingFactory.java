package com.kestrel.weddingbookkeeper.api.wedding.factory;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;

public interface WeddingFactory {

    boolean isSupport(Gender gender);

    Long createWedding(Member member, WeddingInfoRequest weddingInfoRequest);
}
