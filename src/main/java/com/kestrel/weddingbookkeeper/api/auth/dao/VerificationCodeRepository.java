package com.kestrel.weddingbookkeeper.api.auth.dao;

import com.kestrel.weddingbookkeeper.api.auth.vo.VerificationCode;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {

    Optional<VerificationCode> findByMemberId(Long memberId);
}
