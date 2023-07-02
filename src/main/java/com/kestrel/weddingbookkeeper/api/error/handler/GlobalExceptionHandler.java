package com.kestrel.weddingbookkeeper.api.error.handler;

import com.kestrel.weddingbookkeeper.api.base.BaseResponseEntity;
import com.kestrel.weddingbookkeeper.api.error.dto.ErrorResponse;
import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponseEntity<ErrorResponse>> businessExceptionHandle(BusinessException e) {
        log.error("businessException : {} {}", e, e.getMessage());
        return ResponseEntity.status(e.getCode()).body(BaseResponseEntity.fail(e.getMessage()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<BaseResponseEntity<ErrorResponse>> onMissingRequestHeader(MissingRequestHeaderException e) {
        log.error("onMissingRequestHeader : {}", e);
        return ResponseEntity.badRequest().body(BaseResponseEntity.fail(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponseEntity<ErrorResponse>> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException : {}", e);
        return ResponseEntity.badRequest().body(BaseResponseEntity.fail(e.getFieldErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<BaseResponseEntity<ErrorResponse>> methodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException e) {
        log.error("methodArgumentTypeMismatchException : {}", e);
        return ResponseEntity.badRequest().body(BaseResponseEntity.fail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseEntity<ErrorResponse>> allUncaughtHandle(Exception e) {
        log.error("allUncaughtHandle : {}", e);
        return ResponseEntity.internalServerError().body(BaseResponseEntity.fail(e.getMessage()));

    }
}
