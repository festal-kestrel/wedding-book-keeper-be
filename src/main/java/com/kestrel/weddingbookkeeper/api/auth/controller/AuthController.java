package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.facade.AuthFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Integer MEMBER_ID = 1;

    private final AuthFacade authFacade;

    public AuthController(final AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @GetMapping("/verification-code")
    public VerificationCodeResponse getPartnerVerificationCode() {
        return authFacade.getPartnerVerificationCode(MEMBER_ID);
    }

    @PostMapping("/verification-code")
    public void getPartnerVerificationCode(@RequestBody VerificationCodeRequest verificationCodeRequest) {
        authFacade.verifyPartnerVerificationCode(MEMBER_ID, verificationCodeRequest);
    }
}
