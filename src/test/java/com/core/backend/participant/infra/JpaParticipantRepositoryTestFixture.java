package com.core.backend.participant.infra;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.backend.RepositoryTestEnvSupport;
import com.core.backend.group.domain.Group;
import com.core.backend.group.infra.JpaGroupRepository;
import com.core.backend.participant.domain.Participant;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementStatus;
import com.core.backend.settlement.infra.JpaSettlementRepository;
import com.core.backend.user.domain.User;
import com.core.backend.user.infra.database.JpaUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaParticipantRepositoryTestFixture extends RepositoryTestEnvSupport {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	protected JpaUserRepository userRepository;

	@Autowired
	protected JpaSettlementRepository settlementRepository;

	@Autowired
	protected JpaParticipantRepository participantRepository;

	@Autowired
	protected JpaGroupRepository groupRepository;

	protected Group group1;

	protected User user1;
	protected User user2;
	protected User user3;

	protected Settlement settlement1;
	protected Settlement settlement2;
	protected Settlement settlement3;

	protected Participant participant1;
	protected Participant participant2;
	protected Participant participant3;

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

		group1 = Group.of("group");
		group1.addUserToGroup(user1);
		group1.addUserToGroup(user2);
		group1.addUserToGroup(user3);

		groupRepository.save(group1);

		settlement1 = Settlement.builder()
			.settlementName("settlement")
			.totalAmount(30000L)
			.settlementStatus(SettlementStatus.OPEN)
			.groupingAt(LocalDate.now())
			.settlementAt(LocalDate.now())
			.settlementPlace("place")
			.group(group1)
			.build();

		settlement2 = Settlement.builder()
			.settlementName("settlement")
			.totalAmount(30000L)
			.settlementStatus(SettlementStatus.OPEN)
			.groupingAt(LocalDate.now())
			.settlementAt(LocalDate.now())
			.settlementPlace("place")
			.group(group1)
			.build();

		settlement3 = Settlement.builder()
			.settlementName("settlement")
			.totalAmount(30000L)
			.settlementStatus(SettlementStatus.OPEN)
			.groupingAt(LocalDate.now())
			.settlementAt(LocalDate.now())
			.settlementPlace("place")
			.group(group1)
			.build();

		settlementRepository.saveAll(List.of(settlement1, settlement2, settlement3));

		em.flush();
		em.clear();
	}
}
