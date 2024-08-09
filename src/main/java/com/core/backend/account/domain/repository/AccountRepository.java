package com.core.backend.account.domain.repository;

import com.core.backend.account.domain.Account;

public interface AccountRepository {

	Account save(final Account account);

	boolean existByAccountNumber(final String accountNumber);

	Account findByIdAndUserId(final Long accountId, final Long userId);

	boolean existsByUserIdAndMainAccountTrue(final Long userId);

	Account findByUserId(final Long userId);
}
