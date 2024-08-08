package com.core.backend.usergroup.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class UserGroupException extends DefaultException {
	public UserGroupException(ErrorCode errorCode) {
		super(errorCode);
	}

	public UserGroupException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
