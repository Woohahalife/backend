package com.core.backend.common.exception;

import com.core.backend.common.repsonse.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<Object> testExceptionHandler(DefaultException e) {
        log.error("[Exception] message: {} kind: {}", e.getMessage(), e.getClass().getSimpleName());

        return e.getMessage() == null ?
                ResponseEntity.status(e.getErrorCode().getStatusCode()).body(e.getErrorCode()) :
                ResponseEntity.status(e.getErrorCode().getStatusCode()).body(ResultResponse.failure(e.getErrorCode(), e.getMessage()));
    }
}
