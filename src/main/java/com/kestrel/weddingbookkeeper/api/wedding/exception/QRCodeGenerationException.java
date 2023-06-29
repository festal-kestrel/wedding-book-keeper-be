package com.kestrel.weddingbookkeeper.api.wedding.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class QRCodeGenerationException extends BusinessException {

    public QRCodeGenerationException() {
        super(HttpStatus.BAD_REQUEST, "Failed to generate the QR code. The requested data is in an incorrect format.");
    }
}
