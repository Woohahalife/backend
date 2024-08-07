package com.core.backend.account.domain;

import java.util.stream.Stream;

import com.core.backend.account.exception.AccountException;
import com.core.backend.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankCode {

	KOOKMIN("004", "국민은행"),
	WOORI("020", "우리은행"),
	SHINHAN("088", "신한은행"),
	HANA("081", "하나은행"),
	NONGHYUP("011", "농협은행"),
	INDUSTRIAL_BANK_OF_KOREA("003", "기업은행"),
	KEB_HANA("081", "KEB하나은행"),
	SUHYUP("007", "수협은행"),
	POST_OFFICE("071", "우체국"),
	K_BANK("089", "케이뱅크"),
	KAKAO_BANK("090", "카카오뱅크");

	private final String code;
	private final String bankName;

	public static BankCode valueOfBankName(String bankName) {
		return Stream.of(BankCode.values())
			.filter(bankCode -> bankCode.bankName.equals(bankName))
			.findFirst()
			.orElseThrow(() -> new AccountException(ErrorCode.BANK_IS_NOT_FOUND));
	}

	public static BankCode valueOfCode(int codeNum) {
		String code = String.valueOf(codeNum);

		return Stream.of(BankCode.values())
			.filter(bankCode -> bankCode.code.equals(code))
			.findFirst()
			.orElseThrow(() -> new AccountException(ErrorCode.BANK_IS_NOT_FOUND));

	}

}
