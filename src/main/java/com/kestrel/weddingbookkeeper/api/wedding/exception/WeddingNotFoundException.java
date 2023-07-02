package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class WeddingNotFoundException extends BusinessException {

    public WeddingNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Wedding data matching the provided information not found.");
    }
}
