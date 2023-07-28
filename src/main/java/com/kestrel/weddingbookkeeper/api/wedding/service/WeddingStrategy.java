package com.kestrel.weddingbookkeeper.api.wedding.service;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;

public interface WeddingStrategy {

    void connectPartner(Wedding wedding, Member member);

    Wedding getWedding(Member member);
}
