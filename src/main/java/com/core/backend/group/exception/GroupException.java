package com.core.backend.group.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class GroupException extends DefaultException {
	public GroupException(ErrorCode errorCode) {
		super(errorCode);
	}

	public GroupException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
