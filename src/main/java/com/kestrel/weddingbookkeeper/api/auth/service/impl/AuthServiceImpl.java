package com.kestrel.weddingbookkeeper.api.auth.service.impl;

import com.kestrel.weddingbookkeeper.api.auth.dao.VerificationCodeRepository;
import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.exception.VerificationCodeNotFoundException;
import com.kestrel.weddingbookkeeper.api.auth.service.AuthService;
import com.kestrel.weddingbookkeeper.api.auth.vo.VerificationCode;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dao.ManagerVerificationCodeRepository;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.ManagerVerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.utils.VerificationCodeGenerator;
import com.kestrel.weddingbookkeeper.api.wedding.vo.ManagerVerificationCode;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final ManagerVerificationCodeRepository managerVerificationCodeRepository;

    public AuthServiceImpl(final VerificationCodeRepository verificationCodeRepository,
                           final ManagerVerificationCodeRepository managerVerificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.managerVerificationCodeRepository = managerVerificationCodeRepository;
    }

    @Override
    public VerificationCode getPartnerVerificationCode(final Member member) {
        return verificationCodeRepository.findByMemberId(member.getMemberId())
            .filter(code -> !code.isVerified())
            .orElseGet(() -> issuePartnerVerificationCode(member));
    }

    private VerificationCode issuePartnerVerificationCode(final Member member) {
        String verificationCode = VerificationCodeGenerator.generate();
        return verificationCodeRepository.save(
            VerificationCode.of(verificationCode, Role.PARTNER, member.getMemberId())
        );
    }

    @Override
    public Long verifyPartnerVerificationCode(VerificationCodeRequest verificationCodeRequest) {
        VerificationCode verificationCode =
            verificationCodeRepository.findById(verificationCodeRequest.getVerificationCode())
                .filter(code -> !code.isVerified())
                .orElseThrow(VerificationCodeNotFoundException::new);
        verificationCode.verify();
        verificationCodeRepository.save(verificationCode);
        return verificationCode.getMemberId();
    }

    @Override
    public ManagerVerificationCodeResponse verifyManagerVerificationCode(VerificationCodeRequest verificationCodeRequest) {
        ManagerVerificationCode managerVerificationCode =
            managerVerificationCodeRepository.findById(verificationCodeRequest.getVerificationCode())
                .filter(code -> !code.isVerified())
                .orElseThrow(VerificationCodeNotFoundException::new);
        managerVerificationCode.verify();
        managerVerificationCodeRepository.save(managerVerificationCode);
        return new ManagerVerificationCodeResponse(managerVerificationCode.getWeddingId());
    }
}
