package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeddingDao {

    int save(WeddingInsertDto weddingInsertDto);

    int updateQrImgUrl(WeddingUpdateDto weddingUpdateDto);

    Wedding selectWeddingInfo(Integer weddingId);

    Wedding selectByPartnerCode(String partnerCode);

    Member updateGroomPartner(PartnerDto partnerDto);

    Member updateBridePartner(PartnerDto partnerDto);
}
