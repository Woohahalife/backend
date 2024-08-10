package com.core.backend.account.domain.repository;

import com.core.backend.account.domain.Account;
import com.core.backend.common.entity.Status;

import java.util.List;

public interface AccountRepository {

	Account save(final Account account);

	boolean existByAccountNumber(final String accountNumber);

	Account findByIdAndUserId(final Long accountId, final Long userId);

	boolean existsByUserIdAndMainAccountTrue(final Long userId);

	List<Account> findAllByUserId(final Long userId);

	List<Account> findAllByUserIdAndMainAccountTrue(Long userId);
}
