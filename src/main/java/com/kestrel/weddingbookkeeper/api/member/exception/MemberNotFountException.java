package com.kestrel.weddingbookkeeper.api.member.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class MemberNotFountException extends BusinessException {

    public MemberNotFountException() {
        super(HttpStatus.NOT_FOUND, "Member is not found");
    }
}
