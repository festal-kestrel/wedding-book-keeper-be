package com.kestrel.weddingbookkeeper.api.member.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Member is not found");
    }
}
