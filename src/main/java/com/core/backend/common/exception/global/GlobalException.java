package com.core.backend.common.exception.global;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class GlobalException extends DefaultException {

    public GlobalException(ErrorCode errorCode) {
        super(errorCode);
    }

    public GlobalException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
