package com.core.backend.account;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.backend.RepositoryTestEnvSupport;
import com.core.backend.account.domain.Account;
import com.core.backend.account.domain.BankCode;
import com.core.backend.account.infra.JpaAccountRepository;
import com.core.backend.user.domain.User;
import com.core.backend.user.infra.database.JpaUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaAccountRepositoryFixture extends RepositoryTestEnvSupport {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	protected JpaUserRepository userRepository;

	@Autowired
	protected JpaAccountRepository accountRepository;

	protected User user1;

	protected Account account1;

	@BeforeEach
	void setUp() {
		em.createNativeQuery("ALTER TABLE \"user\" AUTO_INCREMENT = 1;")
			.executeUpdate();
		em.createNativeQuery("ALTER TABLE account AUTO_INCREMENT = 1;")
			.executeUpdate();

		user1 = User.of("string1", "string", "string", "01011111111");
		userRepository.save(user1);

		account1 = Account.of(BankCode.KOOKMIN, "0000001111111", user1);
		accountRepository.save(account1);

		em.flush();
		em.clear();
	}
}
