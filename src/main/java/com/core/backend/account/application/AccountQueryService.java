package com.core.backend.account.application;

import com.core.backend.account.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.account.domain.repository.AccountRepository;
import com.core.backend.user.domain.repository.UserRepository;
import com.core.backend.account.ui.dto.AccountResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountQueryService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;

	public List<AccountResponse> getAllAccount(Long userId) {
		List<Account> accounts = accountRepository.findAllByUserId(userId);

		return accounts
			.stream()
			.map(AccountResponse::from)
			.toList();
	}
}
