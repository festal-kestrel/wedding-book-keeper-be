package com.kestrel.weddingbookkeeper.api.member.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class MemberNotRegisteredException extends BusinessException {

    public MemberNotRegisteredException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register new Member.");
    }
}
