package com.core.backend.group;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.backend.ServiceTestEnvSupport;
import com.core.backend.group.application.GroupCommandService;
import com.core.backend.group.application.GroupQueryService;
import com.core.backend.group.domain.Group;
import com.core.backend.group.infra.JpaGroupRepository;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.infra.JpaSettlementRepository;
import com.core.backend.user.domain.User;
import com.core.backend.user.infra.database.JpaUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class GroupServiceTestFixture extends ServiceTestEnvSupport {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	protected GroupCommandService groupCommandService;

	@Autowired
	protected GroupQueryService groupQueryService;

	@Autowired
	protected JpaGroupRepository groupRepository;

	@Autowired
	protected JpaUserRepository userRepository;

	@Autowired
	protected JpaSettlementRepository settlementRepository;

	protected User user1;
	protected User user2;
	protected User user3;

	protected Settlement settlement1;
	protected Settlement settlement2;
	protected Settlement settlement3;

	@BeforeEach
	void setUp() {
		em.createNativeQuery("ALTER TABLE \"group\" AUTO_INCREMENT = 1;")
			.executeUpdate();
		em.createNativeQuery("ALTER TABLE \"user\" AUTO_INCREMENT = 1;")
			.executeUpdate();
		em.createNativeQuery("ALTER TABLE settlement AUTO_INCREMENT = 1;")
			.executeUpdate();
		em.createNativeQuery("ALTER TABLE participant AUTO_INCREMENT = 1;")
			.executeUpdate();

		user1 = User.of("string1", "string", "string", "01011111111");
		user2 = User.of("string2", "string", "string", "01011112222");
		user3 = User.of("string3", "string", "string", "01011113333");
		userRepository.saveAll(List.of(user1, user2, user3));

		em.flush();
		em.clear();
	}
}
