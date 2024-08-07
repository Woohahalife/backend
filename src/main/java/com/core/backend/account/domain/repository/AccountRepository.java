package com.core.backend.account.domain.repository;

import com.core.backend.account.domain.Account;

public interface AccountRepository {

	Account save(final Account account);

	boolean existByAccountNumber(final String accountNumber);
}
