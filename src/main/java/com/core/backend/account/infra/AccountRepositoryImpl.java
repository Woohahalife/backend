package com.core.backend.account.infra;

import org.springframework.stereotype.Repository;

import com.core.backend.account.domain.Account;
import com.core.backend.account.domain.repository.AccountRepository;
import com.core.backend.account.exception.AccountException;
import com.core.backend.common.entity.Status;
import com.core.backend.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

import java.util.List;

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

	@Override
	public Account findByIdAndUserId(Long accountId, Long userId) {
		return repository.findByIdAndUserId(accountId, userId)
			.orElseThrow(() -> new AccountException(ErrorCode.NOT_FOUND_ACCOUNT));
	}

	@Override
	public boolean existsByUserIdAndMainAccountTrue(final Long userId) {
		return repository.existsByUserIdAndMainAccountTrue(userId);
	}

	@Override
	public List<Account> findAllByUserId(final Long userId) {
		return repository.findAllByUserId(userId);
	}

	@Override
	public List<Account> findAllByUserIdAndMainAccountTrue(Long userId) {
		return repository.findAllByUserIdAndMainAccountTrue(userId);
	}
}
