package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class WeddingInfomationNotUpdateException extends BusinessException {

    public WeddingInfomationNotUpdateException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update wedding information.");
    }
}
