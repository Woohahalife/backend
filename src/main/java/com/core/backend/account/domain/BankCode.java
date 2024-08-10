package com.core.backend.account.domain;

import java.util.stream.Stream;

import com.core.backend.account.exception.AccountException;
import com.core.backend.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankCode {

	NONGHYUP("011", "NH농협"),
	KAKAO_BANK("090", "카카오뱅크"),
	KOOKMIN("004", "KB국민"),
	TOSS("092", "토스뱅크"),
	SHINHAN("088", "신한"),
	WOORI("020", "우리"),
	INDUSTRIAL_BANK_OF_KOREA("003", "IBK기업"),
	HANA("081", "하나"),
	SAEMAUL("036", "새마을"),
	BUSAN("034", "부산"),
	K_BANK("089", "케이뱅크"),
	SINHYUP("048", "신협"),
	POST_OFFICE("071", "우체국"),
	SUHYUP("007", "수협"),
	JEJU("035", "제주");

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
