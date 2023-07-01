package com.kestrel.weddingbookkeeper.api.member.exception;

import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidRoleNameException extends BusinessException {

    public InvalidRoleNameException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Role Name");
    }
}
