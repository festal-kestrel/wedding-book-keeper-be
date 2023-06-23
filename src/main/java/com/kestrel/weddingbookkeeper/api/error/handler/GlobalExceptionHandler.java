package com.kestrel.weddingbookkeeper.api.error.handler;

import com.kestrel.weddingbookkeeper.api.error.dto.ErrorResponse;
import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandle(final BusinessException e) {
        log.error("businessException : {} {}", e, e.getMessage());
        return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponse> onMissingRequestHeader(MissingRequestHeaderException e) {
        log.error("onMissingRequestHeader : {}", e);
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException : {}", e);
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getFieldErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> allUncaughtHandle(Exception e) {
        log.error("allUncaughtHandle : {}", e);
        return ResponseEntity.internalServerError().build();
    }
}
