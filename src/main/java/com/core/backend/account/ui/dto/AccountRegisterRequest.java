package com.core.backend.account.ui.dto;

import com.core.backend.account.application.dto.AccountRegisterServiceRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRegisterRequest {

	private String bankName;
	private String accountNumber;
}
