package com.core.backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "중복되는 이메일입니다."),
	DUPLICATE_PHONE_NUMBER(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "중복되는 휴대폰 번호입니다."),
	DUPLICATE_ACCOUNT_NUMBER(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "중복되는 계좌번호 입니다."),
	ALREADY_ENTRANCE_USER(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "이미 입장한 사용자입니다."),

	NOT_FOUND_ACCOUNT(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당하는 계좌 정보가 존재하지 않습니다"),
	NOT_FOUND_SETTLEMENT(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당하는 정산 정보가 존재하지 않습니다"),
	BANK_IS_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value() ,"일치하는 은행이 없습니다."),
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "유효한 사용자를 찾을 수 없습니다."),
	NOT_FOUND_GROUP(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "유효한 모임방을 찾을 수 없습니다."),
	INVALID_GROUP_FOR_USER(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "사용자가 접근할 수 없는 모임입니다."),
	NOT_FOUND_GROUP_MEMBER(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당 모임에 참여한 사용자가 아닙니다."),
	NOT_FOUND_SETTLEMENT_PARTICIPANT(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당 정산에 참여한 참가자가 아닙니다."),

	CONFLICT(HttpStatus.CONFLICT, HttpStatus.Series.CLIENT_ERROR.value(), "중복된 데이터 항목이 감지되었습니다.(무결성 제약 조건 위반)"),

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
