package com.core.backend.participant.exception;

import com.core.backend.common.exception.DefaultException;
import com.core.backend.common.exception.ErrorCode;

public class ParticipantException extends DefaultException {
	public ParticipantException(ErrorCode errorCode) {
		super(errorCode);
	}

	public ParticipantException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
