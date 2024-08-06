package com.core.backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ALREADY_DELETED_DATA(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "이미 삭제처리 된 데이터입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "inertnal_server_error");

    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String message;
}
