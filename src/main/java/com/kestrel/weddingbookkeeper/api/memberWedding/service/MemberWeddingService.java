package com.kestrel.weddingbookkeeper.api.memberWedding.service;

import com.kestrel.weddingbookkeeper.api.memberWedding.vo.MemberWedding;
import java.util.List;

public interface MemberWeddingService {

    List<MemberWedding> selectDonationList(Integer memberId);
}
