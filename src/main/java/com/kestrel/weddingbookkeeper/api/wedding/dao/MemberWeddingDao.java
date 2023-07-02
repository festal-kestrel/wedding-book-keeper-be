package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberWeddingDao {

    List<MemberWedding> selectDonationList(Integer memberId);

    List<MemberWedding> selectGuestsByWeddingId(Integer weddingId);

    List<MemberWedding> selectGuestsByWeddingIdAndHasPaid(Integer weddingId);
}
