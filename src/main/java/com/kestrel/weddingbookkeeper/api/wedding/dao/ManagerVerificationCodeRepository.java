package com.kestrel.weddingbookkeeper.api.wedding.dao;

import com.kestrel.weddingbookkeeper.api.wedding.vo.ManagerVerificationCode;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ManagerVerificationCodeRepository extends CrudRepository<ManagerVerificationCode, String> {

    Optional<ManagerVerificationCode> findByWeddingId(Long weddingId);
}
