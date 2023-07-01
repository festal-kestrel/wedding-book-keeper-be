package com.kestrel.weddingbookkeeper.api.auth.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class VerificationCodeNotFoundException extends BusinessException {

    public VerificationCodeNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Verification code not found");
    }
}
