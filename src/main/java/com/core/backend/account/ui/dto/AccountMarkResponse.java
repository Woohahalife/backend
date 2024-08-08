package com.core.backend.account.ui.dto;

import com.core.backend.account.domain.Account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccountMarkResponse {

	private Long accountId;
	private boolean isMainAccount;

	public static AccountMarkResponse from(Account account) {
		return new AccountMarkResponse(account.getId(), account.isMainAccount());
	}
}
