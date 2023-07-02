package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.facade.AuthFacade;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("배우자 연결에 필요한 인증코드 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = VerificationCodeResponse.class),
    })
    public VerificationCodeResponse getPartnerVerificationCode() {
        return authFacade.getPartnerVerificationCode(MEMBER_ID);
    }

    @PostMapping("/verification-code")
    @ApiOperation("배우자 인증코드 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void getPartnerVerificationCode(@RequestBody VerificationCodeRequest verificationCodeRequest) {
        authFacade.verifyPartnerVerificationCode(MEMBER_ID, verificationCodeRequest);
    }
}
