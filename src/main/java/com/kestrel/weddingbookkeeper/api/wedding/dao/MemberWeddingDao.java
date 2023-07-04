package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingSaveDto;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberWeddingDao {

    List<MemberWedding> selectDonationList(Integer memberId);

    List<MemberWedding> selectGuestsByWeddingId(Integer weddingId);

    List<MemberWedding> selectGuestsByWeddingIdAndHasPaid(Integer weddingId);

    int insertMemberWedding(MemberWeddingSaveDto memberWeddingSaveDto);


    void patchDonationApproval(@Param("weddingId") Integer weddingId, @Param("memberId") Integer memberId);

    void patchDonationRejection(@Param("weddingId") Integer weddingId, @Param("memberId") Integer memberId);

}
