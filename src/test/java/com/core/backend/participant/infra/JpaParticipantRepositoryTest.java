package com.core.backend.participant.infra;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.participant.domain.Participant;

class JpaParticipantRepositoryTest extends JpaParticipantRepositoryTestFixture {

	@Test
	@DisplayName("동의 여부가 false인 상태의 participant만 조회할 수 있다.")
	void findByUserIdAndAgreementStatusFalseTest() {
		//given
		participant1 = Participant.of("participant1", 10000L, user1, settlement1);
		participant2 = Participant.of("participant2", 10000L, user1, settlement2);
		participant3 = Participant.of("participant3", 10000L, user1, settlement3);

		participantRepository.saveAll(List.of(participant1, participant2, participant3));

		participant1.markAsAgreed(); // 동의여부 승낙

		// when
		List<Participant> result = participantRepository.findByUserIdAndAgreementStatusFalse(user1.getId());

		// then
		Assertions.assertThat(result).hasSize(2);
	}

	@Test
	@DisplayName("사용자가 참가하고 있는 모든 참가 내역을 조회할 수 있다.")
	void findAllByUserIdTest() {
	    // given
		participant1 = Participant.of("participant1", 10000L, user1, settlement1);
		participant2 = Participant.of("participant2", 10000L, user1, settlement2);
		participant3 = Participant.of("participant3", 10000L, user1, settlement3);

		participantRepository.saveAll(List.of(participant1, participant2, participant3));


	    // when
		List<Participant> result = participantRepository.findAllByUserId(user1.getId());

		// then
		Assertions.assertThat(result).hasSize(3);
		Assertions.assertThat(result.get(2).getId()).isEqualTo(3L);
	}
}
