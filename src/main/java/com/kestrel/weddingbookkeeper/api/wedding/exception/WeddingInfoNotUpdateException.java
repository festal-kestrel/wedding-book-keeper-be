package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class WeddingInfoNotUpdateException extends BusinessException {

    public WeddingInfoNotUpdateException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Wedding Info Not Update");
    }
}
