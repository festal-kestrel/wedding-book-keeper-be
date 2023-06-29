package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidGenderException extends BusinessException {

    public InvalidGenderException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Gender type.");
    }
}
