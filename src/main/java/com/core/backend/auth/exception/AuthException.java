package com.core.backend.auth.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class AuthException extends DefaultException {
	public AuthException(ErrorCode errorCode) {
		super(errorCode);
	}

	public AuthException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
