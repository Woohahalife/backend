package com.core.backend.account.ui.dto;

import com.core.backend.account.domain.Account;

import com.core.backend.account.domain.BankCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccountResponse {

	private Long id;
	private boolean isMainAccount;
    private String bankName;
    private Long balance;
    private String accountNumber;

	public static AccountResponse from(Account account) {
		return new AccountResponse(
        account.getId(),
        account.isMainAccount(),
        account.getBankCode().getBankName(),
        account.getBalance(),
        account.getAccountNumber()
        );
	}
}
