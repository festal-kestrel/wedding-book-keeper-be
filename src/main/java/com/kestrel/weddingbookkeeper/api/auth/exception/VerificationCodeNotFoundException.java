package com.kestrel.weddingbookkeeper.api.auth.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class VerificationCodeNotFoundException extends BusinessException {

    public VerificationCodeNotFoundException() {
        super(HttpStatus.NOT_FOUND,
                "Expired or Invalid Verification Code: The verification code you provided has either expired or is invalid. Please request a new code or verify the correctness of the entered code.");
    }
}
