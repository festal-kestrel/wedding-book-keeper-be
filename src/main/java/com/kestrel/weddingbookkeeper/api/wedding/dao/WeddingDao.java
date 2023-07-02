package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.dto.PartnerDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInsertDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingUpdateDto;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeddingDao {

    int save(WeddingInsertDto weddingInsertDto);

    int updateQrImgUrl(WeddingUpdateDto weddingUpdateDto);

    Wedding selectWeddingInfo(Integer weddingId);

    Wedding selectByPartnerCode(String partnerCode);

    int updateGroomPartner(PartnerDto partnerDto);

    int updateBridePartner(PartnerDto partnerDto);

    int updateWeddingInformation(WeddingInfoUpdateDto weddingInfoUpdateDto);

    Wedding selectManagerCode(Integer weddingId);

    Wedding selectQrImgUrl(Integer weddingId);

    Optional<Wedding> selectByGroomId(Integer memberId);

    Optional<Wedding> selectByBrideId(Integer memberId);
}
