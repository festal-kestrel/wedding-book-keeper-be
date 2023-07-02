package com.kestrel.weddingbookkeeper.api.error.handler;

import com.kestrel.weddingbookkeeper.api.base.BaseResponseEntity;
import com.kestrel.weddingbookkeeper.api.error.dto.ErrorResponse;
import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponseEntity<ErrorResponse> businessExceptionHandle(BusinessException e) {
        log.error("businessException : {} {}", e, e.getMessage());
        return BaseResponseEntity.fail(e.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public BaseResponseEntity<ErrorResponse> onMissingRequestHeader(MissingRequestHeaderException e) {
        log.error("onMissingRequestHeader : {}", e);
        return BaseResponseEntity.fail(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException : {}", e);
        return BaseResponseEntity.fail(e.getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected BaseResponseEntity<ErrorResponse> methodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException e) {
        log.error("methodArgumentTypeMismatchException : {}", e);
        return BaseResponseEntity.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponseEntity<ErrorResponse> allUncaughtHandle(Exception e) {
        log.error("allUncaughtHandle : {}", e);
        return BaseResponseEntity.fail(e.getMessage());
    }
}
