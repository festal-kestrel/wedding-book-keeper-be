package com.kestrel.weddingbookkeeper.api.auth.service;

import com.kestrel.weddingbookkeeper.api.auth.dto.ManagerVerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.vo.PartnerVerificationCode;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.ManagerVerificationCodeResponse;

public interface AuthService {

    PartnerVerificationCode getPartnerVerificationCode(Member member);

    PartnerVerificationCode verifyPartnerVerificationCode(VerificationCodeRequest verificationCodeRequest);

    VerificationCodeResponse getManagerVerificationCode(ManagerVerificationCodeRequest managerVerificationCodeRequest);

    ManagerVerificationCodeResponse verifyManagerVerificationCode(VerificationCodeRequest verificationCodeRequest);
}
