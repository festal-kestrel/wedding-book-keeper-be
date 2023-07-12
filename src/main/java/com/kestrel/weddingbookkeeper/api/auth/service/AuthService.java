package com.kestrel.weddingbookkeeper.api.auth.service;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.vo.VerificationCode;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.ManagerVerificationCodeResponse;

public interface AuthService {

    VerificationCode getPartnerVerificationCode(Member member);

    Long verifyPartnerVerificationCode(VerificationCodeRequest verificationCodeRequest);

    ManagerVerificationCodeResponse verifyManagerVerificationCode(VerificationCodeRequest verificationCodeRequest);
}
