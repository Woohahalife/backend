package com.core.backend.account.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.account.domain.repository.AccountRepository;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountQueryService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
}
