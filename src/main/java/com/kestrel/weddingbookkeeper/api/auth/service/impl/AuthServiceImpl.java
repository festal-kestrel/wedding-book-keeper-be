package com.kestrel.weddingbookkeeper.api.auth.service.impl;

import com.kestrel.weddingbookkeeper.api.auth.dao.VerificationCodeRepository;
import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.exception.VerificationCodeNotFoundException;
import com.kestrel.weddingbookkeeper.api.auth.service.AuthService;
import com.kestrel.weddingbookkeeper.api.auth.vo.VerificationCode;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.util.VerificationCodeGenerator;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final VerificationCodeRepository verificationCodeRepository;

    public AuthServiceImpl(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public String getPartnerVerificationCode(Member member) {
        if (!member.isPartnerCodeIssued()) {
            return issuePartnerVerificationCode(member);
        }
        return findPartnerVerificationCode(member);
    }

    @Override
    public Integer verifyPartnerVerificationCode(VerificationCodeRequest verificationCodeRequest) {
        VerificationCode verificationCode = verificationCodeRepository.findById(verificationCodeRequest.getVerificationCode())
                .orElseThrow(VerificationCodeNotFoundException::new);
        if (verificationCode.getRole() != Role.PARTNER || verificationCode.isVerified()) {
            throw new VerificationCodeNotFoundException();
        }
        if (!verificationCode.getVerificationCode().equals(verificationCodeRequest.getVerificationCode())) {
            throw new VerificationCodeNotFoundException();
        }
        verificationCode.verify();
        verificationCodeRepository.save(verificationCode);
        return verificationCode.getMemberId();
    }

    private String issuePartnerVerificationCode(Member member) {
        String verificationCode = VerificationCodeGenerator.generate();
        verificationCodeRepository.save(VerificationCode.of(verificationCode, Role.PARTNER, member.getMemberId()));
        return verificationCode;
    }

    private String findPartnerVerificationCode(Member member) {
        try {
            VerificationCode verificationCode = verificationCodeRepository.findByMemberId(member.getMemberId())
                    .orElseThrow(VerificationCodeNotFoundException::new);
            if (verificationCode.getRole() != Role.PARTNER || verificationCode.isVerified()) {
                throw new VerificationCodeNotFoundException();
            }
            return verificationCode.getVerificationCode();
        } catch (VerificationCodeNotFoundException e) {
            return issuePartnerVerificationCode(member);
        }
    }
}
