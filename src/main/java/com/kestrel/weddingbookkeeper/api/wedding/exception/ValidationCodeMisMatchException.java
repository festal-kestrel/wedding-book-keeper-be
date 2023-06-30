package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ValidationCodeMisMatchException extends BusinessException {

    public ValidationCodeMisMatchException() {
        super(HttpStatus.BAD_REQUEST, "Validation code mismatch. Please check the code and try again.");
    }
}
