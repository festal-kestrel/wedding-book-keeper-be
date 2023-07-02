package com.kestrel.weddingbookkeeper.api.wedding.factory;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;

public interface WeddingFactory {

    boolean isSupport(Gender gender);

    void connectPartner(Member member, Member partner);
}
