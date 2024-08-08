package com.core.backend.account.infra;

import org.springframework.stereotype.Repository;

import com.core.backend.account.domain.Account;
import com.core.backend.account.domain.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

	private final JpaAccountRepository repository;

	@Override
	public Account save(final Account account) {
		return repository.save(account);
	}

	@Override
	public boolean existByAccountNumber(final String accountNumber) {
		return repository.existsByAccountNumber(accountNumber);
	}
}
