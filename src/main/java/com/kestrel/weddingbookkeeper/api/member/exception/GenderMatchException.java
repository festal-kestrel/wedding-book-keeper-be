package com.kestrel.weddingbookkeeper.api.member.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class GenderMatchException extends BusinessException {

    public GenderMatchException() {
        super(HttpStatus.BAD_REQUEST, "Invalid gender combination. Male and female partners are required.");
    }
}
