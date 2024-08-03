package com.core.backend.common.exception;

public interface ErrorCode {

    String name();
    boolean isSuccess();
    int getStatusCode();
    String getMessage();
}
