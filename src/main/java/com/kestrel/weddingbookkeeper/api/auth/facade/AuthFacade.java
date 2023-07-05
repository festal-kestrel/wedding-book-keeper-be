package com.kestrel.weddingbookkeeper.api.auth.facade;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerifyPartnerVerificationCodeResponse;

public interface AuthFacade {

    VerificationCodeResponse getPartnerVerificationCode(Long memberId);

    VerifyPartnerVerificationCodeResponse verifyPartnerVerificationCode(Long memberId, VerificationCodeRequest verificationCodeRequest);
}
