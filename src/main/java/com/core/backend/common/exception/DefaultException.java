package com.core.backend.common.exception;

import lombok.Getter;

@Getter
public abstract class DefaultException extends RuntimeException {

    private final ErrorCode errorCode;

    protected DefaultException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected DefaultException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
