package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingSaveDto;
import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.vo.NotificationInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberWeddingDao {

    List<MemberWedding> selectDonationList(Integer memberId);

    List<MemberWedding> selectGuestsByWeddingId(Integer weddingId);

    List<MemberWedding> selectGuestsByWeddingIdAndHasPaid(Integer weddingId);

    int insertMemberWedding(MemberWeddingSaveDto memberWeddingSaveDto);

    List<NotificationInfo> selectFcmTokenByWeddingId(Integer weddingId);

}
