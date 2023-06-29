package com.kestrel.weddingbookkeeper.api.memberWedding.dao;

import com.kestrel.weddingbookkeeper.api.memberWedding.vo.MemberWedding;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberWeddingDao {

    List<MemberWedding> selectDonationList(Integer memberId);
}
