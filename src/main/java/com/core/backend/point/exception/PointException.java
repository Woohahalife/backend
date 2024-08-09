package com.core.backend.point.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class PointException extends DefaultException {
	public PointException(ErrorCode errorCode) {
		super(errorCode);
	}

	public PointException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
