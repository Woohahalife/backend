package com.core.backend.account;

import com.core.backend.user.domain.User;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.backend.ServiceTestEnvSupport;
import com.core.backend.account.application.AccountCommandService;
import com.core.backend.account.application.AccountQueryService;
import com.core.backend.account.infra.JpaAccountRepository;
import com.core.backend.user.infra.database.JpaUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class AccountServiceTestFixture extends ServiceTestEnvSupport {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	protected AccountCommandService accountCommandService;

	@Autowired
	protected AccountQueryService accountQueryService;

	@Autowired
	protected JpaAccountRepository accountRepository;

	@Autowired
	protected JpaUserRepository userRepository;

	protected User user1;

	@BeforeEach
	void setUp() {
		em.createNativeQuery("ALTER TABLE \"user\" AUTO_INCREMENT = 1;")
			.executeUpdate();
		em.createNativeQuery("ALTER TABLE account AUTO_INCREMENT = 1;")
			.executeUpdate();

		user1 = User.of("string1", "string", "string", "01011111111");
		userRepository.saveAll(List.of(user1));

		em.flush();
		em.clear();
	}
}
