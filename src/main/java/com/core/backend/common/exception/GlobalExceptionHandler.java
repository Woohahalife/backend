package com.core.backend.common.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.core.backend.common.repsonse.ResultResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DefaultException.class)
	public ResponseEntity<Object> defaultExceptionHandler(DefaultException e) {
		log.error("[Exception] status: {} message: {} kind: {}", e.getErrorCode().getHttpStatus(), e.getMessage(),
			e.getClass().getSimpleName());

		return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(ResultResponse.failure(e.getErrorCode()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> unExpectedExceptionHandler(Exception e) {
		log.error("[INTERNAL_SERVER_ERROR]", e);

		return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode())
			.body(ResultResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
			log.error("[409 : CLIENT_CONFLICT_ERROR]", e);
			return ResponseEntity.status(ErrorCode.CONFLICT.getStatusCode())
				.body(ResultResponse.failure(ErrorCode.CONFLICT));
	}
}
