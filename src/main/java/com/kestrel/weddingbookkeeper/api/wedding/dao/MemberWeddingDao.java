package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingSaveDto;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.vo.NotificationInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberWeddingDao {

    List<MemberWedding> selectDonationList(Long memberId);

    List<MemberWedding> selectGuestsByWeddingId(Integer weddingId);

    List<MemberWedding> selectGuestsByWeddingIdAndHasPaid(Integer weddingId);

    int insertMemberWedding(MemberWeddingSaveDto memberWeddingSaveDto);


    List<NotificationInfo> selectFcmTokenByWeddingId(Long weddingId);

    void patchDonationApproval(@Param("weddingId") Long weddingId, @Param("memberId") Long memberId);

    void patchDonationRejection(@Param("weddingId") Long weddingId, @Param("memberId") Long memberId);

}
