package com.core.backend.user.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class UserException extends DefaultException {
	public UserException(ErrorCode errorCode) {
		super(errorCode);
	}

	public UserException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
