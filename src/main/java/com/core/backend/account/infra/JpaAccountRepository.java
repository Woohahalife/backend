package com.core.backend.account.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.backend.account.domain.Account;

public interface JpaAccountRepository extends JpaRepository<Account, Long> {

	boolean existsByAccountNumber(final String accountNumber);

	Optional<Account> findByIdAndUserId(final Long accountId, final Long userId);
}
