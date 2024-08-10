package com.core.backend.account.application;

import java.util.List;

import com.core.backend.account.domain.BankCode;
import com.core.backend.account.ui.dto.AccountResponse;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.account.AccountServiceTestFixture;
import com.core.backend.account.domain.Account;
import com.core.backend.user.domain.User;

class AccountQueryServiceTest extends AccountServiceTestFixture {

	@Test
	@DisplayName("사용자가 등록한 계좌 목록을 조회할 수 있다.")
	void getAllAccountTest() {
		// given
		AuthUser authUser = new AuthUser(user1.getId());

		Account account1 = setAccountData(BankCode.SHINHAN, "123", user1);
		Account account2 = setAccountData(BankCode.WOORI, "456", user1);
		Account account3 = setAccountData(BankCode.KOOKMIN, "789", user1);

		accountRepository.saveAll(List.of(account1, account2, account3));

		// when
		List<AccountResponse> responses = accountQueryService.getAllAccount(authUser.getUserId());

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(responses).hasSize(3);
			softAssertions.assertThat(responses)
				.extracting("bankName", "accountNumber")
				.containsExactly(
					Tuple.tuple(BankCode.SHINHAN.getBankName(), "123"),
					Tuple.tuple(BankCode.WOORI.getBankName(), "456"),
					Tuple.tuple(BankCode.KOOKMIN.getBankName(), "789")
				);
		});
	}

	private Account setAccountData(BankCode bankCode, String accountNumber, User user) {
		Account account = Account.of(bankCode, accountNumber, user);
        user.addAccount(account);
		return account;
	}
}
