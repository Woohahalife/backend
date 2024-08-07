package com.core.backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "중복되는 이메일입니다."),
	DUPLICATE_PHONE_NUMBER(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "중복되는 휴대폰 번호입니다."),

	NOT_VALID_USER(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "유효한 사용자를 찾을 수 없습니다."),

	INVALID_TOKEN_FORMAT(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "올바른 토큰 형식이 아닙니다."),
	BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "사용자 정보가 잘못되었습니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "토큰 유효기간이 만료되었습니다."),
	INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "서명 정보가 잘못되었습니다."),

	ALREADY_DELETED_DATA(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "이미 삭제처리 된 데이터입니다."),

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "inertnal_server_error");

	private final HttpStatus httpStatus;
	private final int statusCode;
	private final String message;
}
