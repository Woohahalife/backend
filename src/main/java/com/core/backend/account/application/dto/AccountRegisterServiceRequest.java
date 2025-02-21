package com.core.backend.account.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRegisterServiceRequest {

	private String bankName;
	private String accountNumber;

	public static AccountRegisterServiceRequest of(String bankName, String accountNumber) {
		return new AccountRegisterServiceRequest(bankName, accountNumber);
	}
}
