package com.kestrel.weddingbookkeeper.api.error.handler;

import static org.junit.jupiter.api.Assertions.*;

import com.kestrel.weddingbookkeeper.api.error.dto.ErrorResponse;
import com.kestrel.weddingbookkeeper.api.error.exception.BusinessException;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void businessException이_발생하면_설정한_에러코드와_메세지를_반환한다() {
        // given
        BusinessException exception = new BusinessException(HttpStatus.BAD_REQUEST, "bad request");

        // when
        ResponseEntity<ErrorResponse> response = exceptionHandler.businessExceptionHandle(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("bad request", Objects.requireNonNull(response.getBody()).getMessage());
    }
}
