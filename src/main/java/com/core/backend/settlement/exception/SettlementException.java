package com.core.backend.settlement.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class SettlementException extends DefaultException {
	public SettlementException(ErrorCode errorCode) {
		super(errorCode);
	}

	public SettlementException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
