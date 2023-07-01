package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class WeddingInfoNotSavedException extends BusinessException {

    public WeddingInfoNotSavedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save wedding information.");
    }
}
