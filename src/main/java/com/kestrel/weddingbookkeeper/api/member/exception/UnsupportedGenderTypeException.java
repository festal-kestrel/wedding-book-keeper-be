package com.kestrel.weddingbookkeeper.api.member.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UnsupportedGenderTypeException extends BusinessException {

    public UnsupportedGenderTypeException() {
        super(HttpStatus.BAD_REQUEST, "Unsupported gender type");
    }
}
