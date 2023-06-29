package com.kestrel.weddingbookkeeper.external.storage.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AwsS3Exception extends BusinessException {

    public AwsS3Exception() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the file to AWS S3.");
    }
}
