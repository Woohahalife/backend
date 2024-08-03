package com.core.backend.common.repsonse;

import com.core.backend.common.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "statusCode", "message", "status", "data"})
public final class ResultResponse<T> {

    private Boolean isSuccess;
    private int statusCode;
    private String message;
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(
                true,
                HttpStatus.OK.value(),
                "요청이 성공적으로 처리되었습니다.",
                HttpStatus.OK.name(),
                data);
    }

    public static <T> ResultResponse<T> failure(ErrorCode errorCode) {
        return new ResultResponse<>(
                errorCode.isSuccess(),
                errorCode.getStatusCode(),
                errorCode.getMessage(),
                errorCode.name(),
                null);
    }

    public static <T> ResultResponse<T> failure(ErrorCode errorCode, String message) {
        return new ResultResponse<>(
                errorCode.isSuccess(),
                errorCode.getStatusCode(),
                message,
                errorCode.name(),
                null);
    }
}