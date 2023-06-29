package com.kestrel.weddingbookkeeper.api.auth.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class JwtTokenNotSavedException extends BusinessException {

    public JwtTokenNotSavedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save JWT Token.");
    }
}
