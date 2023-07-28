package com.kestrel.weddingbookkeeper.api.auth.controller;

import com.kestrel.weddingbookkeeper.api.auth.dto.ManagerVerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.request.VerificationCodeRequest;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerifyPartnerVerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.facade.AuthFacade;
import com.kestrel.weddingbookkeeper.api.auth.service.AuthService;
import com.kestrel.weddingbookkeeper.api.auth.utils.WeddingMember;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.ManagerVerificationCodeResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API", description = "배우자 & 관리자 인증 API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthFacade authFacade;
    private final AuthService authService;

    public AuthController(final AuthFacade authFacade, final AuthService authService) {
        this.authFacade = authFacade;
        this.authService = authService;
    }

    @GetMapping("/verification-code")
    @Operation(summary = "배우자 연결에 필요한 인증코드 조회", tags = {"Auth API"})
    @ApiResponses({
        @ApiResponse(code = 200, message = "successful operation", response = VerificationCodeResponse.class),
    })
    public VerificationCodeResponse getPartnerVerificationCode(@AuthenticationPrincipal WeddingMember weddingMember) {
        return authFacade.getPartnerVerificationCode(weddingMember.getMemberId());
    }

    @PostMapping("/verification-code")
    @Operation(summary = "배우자 인증코드 인증", tags = {"Auth API"})
    @ApiResponses({
        @ApiResponse(code = 200, message = "successful operation"),
    })
    public VerifyPartnerVerificationCodeResponse getPartnerVerificationCode(@RequestBody final VerificationCodeRequest verificationCodeRequest,
                                                                            @AuthenticationPrincipal final WeddingMember weddingMember) {
        return authFacade.verifyPartnerVerificationCode(weddingMember.getMemberId(), verificationCodeRequest);
    }

    @GetMapping("/verification-code/admin")
    @Operation(summary = "웨딩 관리자 인증코드 조회", tags = {"Auth API"})
    @ApiResponses({
        @ApiResponse(code = 200, message = "successful operation", response = VerificationCodeResponse.class),
    })
    public VerificationCodeResponse getManagerVerificationCode(@RequestBody final ManagerVerificationCodeRequest managerVerificationCodeRequest) {
        return authService.getManagerVerificationCode(managerVerificationCodeRequest);
    }

    @PostMapping("/verification-code/admin")
    @Operation(summary = "웨딩 관리자 인증코드 인증", tags = {"Auth API"})
    @ApiResponses({
        @ApiResponse(code = 200, message = "successful operation"),
    })
    public ManagerVerificationCodeResponse verifyManagerVerificationCode(@RequestBody final VerificationCodeRequest verificationCodeRequest) {
        return authService.verifyManagerVerificationCode(verificationCodeRequest);
    }
}
