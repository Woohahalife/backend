package com.core.backend.account.infra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.account.JpaAccountRepositoryFixture;
import com.core.backend.account.domain.Account;

public class JpaAccountRepositoryTest extends JpaAccountRepositoryFixture {

	@Test
	@DisplayName("대표계좌가 존재하는지 확인할 수 있다.(대표계좌가 없는 경우)")
	void existsByUserIdAndMainAccountFalseTest() {
	    // when & then
		Assertions.assertThat(accountRepository.existsByUserIdAndMainAccountTrue(user1.getId())).isFalse();
	}

	@Test
	@DisplayName("대표계좌가 존재하는지 확인할 수 있다.(대표계좌가 존재하는 경우)")
	void existsByUserIdAndMainAccountTrueTest() {
		// given
		Account account = accountRepository.findById(account1.getId()).get();// 더티체킹
		account.setMainAccount(true);

		// when & then
		Assertions.assertThat(accountRepository.existsByUserIdAndMainAccountTrue(user1.getId())).isTrue();
	}
}
