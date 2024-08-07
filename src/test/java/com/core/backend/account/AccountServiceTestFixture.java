package com.core.backend.account;

import org.springframework.beans.factory.annotation.Autowired;

import com.core.backend.ServiceTestEnvSupport;
import com.core.backend.account.application.AccountCommandService;
import com.core.backend.account.application.AccountQueryService;
import com.core.backend.account.infra.JpaAccountRepository;
import com.core.backend.user.infra.database.JpaUserRepository;

public class AccountServiceTestFixture extends ServiceTestEnvSupport {

	@Autowired
	protected AccountCommandService accountCommandService;

	@Autowired
	protected AccountQueryService accountQueryService;

	@Autowired
	protected JpaAccountRepository accountRepository;

	@Autowired
	protected JpaUserRepository userRepository;
}
