package com.core.backend.account.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class AccountException extends DefaultException {
	public AccountException(ErrorCode errorCode) {
		super(errorCode);
	}

	public AccountException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
