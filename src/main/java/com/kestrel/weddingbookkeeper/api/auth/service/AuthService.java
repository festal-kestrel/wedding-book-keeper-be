package com.kestrel.weddingbookkeeper.api.auth.service;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;

public interface AuthService {

    String getPartnerVerificationCode(Member member);

    Integer verifyPartnerVerificationCode(VerificationCodeRequest verificationCodeRequest);
}
