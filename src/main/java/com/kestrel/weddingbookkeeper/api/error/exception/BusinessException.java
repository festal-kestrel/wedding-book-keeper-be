package com.kestrel.weddingbookkeeper.api.error.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(HttpStatus httpStatus, String message) {
        super(message);
        this.code = httpStatus.value();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return super.getMessage();
    }
}
