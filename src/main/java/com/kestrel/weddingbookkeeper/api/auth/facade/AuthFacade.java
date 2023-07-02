package com.kestrel.weddingbookkeeper.api.auth.facade;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;

public interface AuthFacade {

    VerificationCodeResponse getPartnerVerificationCode(Integer memberId);

    void verifyPartnerVerificationCode(Integer memberId, VerificationCodeRequest verificationCodeRequest);
}
