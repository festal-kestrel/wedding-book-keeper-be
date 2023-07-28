package com.kestrel.weddingbookkeeper.api.auth.dao;

import com.kestrel.weddingbookkeeper.api.auth.vo.PartnerVerificationCode;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface VerificationCodeRepository extends CrudRepository<PartnerVerificationCode, String> {

    Optional<PartnerVerificationCode> findByMemberId(Long memberId);
}
