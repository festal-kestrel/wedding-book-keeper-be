package com.kestrel.weddingbookkeeper.api.wedding.factory;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;

public interface WeddingFactory {

    boolean isSupport(Gender gender);

    Long createWedding(Member member, WeddingInfoRequest weddingInfoRequest);

    Long connectPartner(Member member, Member partner);

    Wedding getWedding(Member member);
}
