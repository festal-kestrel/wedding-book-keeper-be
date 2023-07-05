package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyVerifiedException extends BusinessException {

    public AlreadyVerifiedException() {
        super(HttpStatus.CONFLICT, "The verification has already been completed. It is not possible to attempt verification again due to the existing verified status.");
    }
}
