package com.core.backend.settlement.application;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.group.domain.Group;
import com.core.backend.participant.domain.Participant;
import com.core.backend.settlement.SettlementServiceTestFixture;
import com.core.backend.settlement.application.dto.SettlementParticipantServiceRequest;
import com.core.backend.settlement.application.dto.SettlementRegisterServiceRequest;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementStatus;
import com.core.backend.settlement.exception.SettlementException;

class SettlementCommandServiceTest extends SettlementServiceTestFixture {

	@Test
	@DisplayName("모임 참여자들에게 정산을 요청한다.")
	void requestSettlementTest() {
		// given
		SettlementRegisterServiceRequest serviceRequest = getSettlementRegisterServiceRequest();

		// when
		settlementCommandService.requestSettlement(user1.getId(), group1.getId(), serviceRequest);

		// then
		Settlement settlement = settlementRepository.findById(1L).get();

		Assertions.assertThat(settlement).isNotNull();
		Assertions.assertThat(settlement)
			.extracting("settlementName", "totalAmount")
			.contains("name", 30000L);
	}

	@Test
	@DisplayName("정산을 요청한 참여자는 자동으로 정산에 동의한 것으로 간주한다.")
	void settlementRequestAutoAgreementTest() {
		// given
		SettlementRegisterServiceRequest serviceRequest = getSettlementRegisterServiceRequest();

		// when
		settlementCommandService.requestSettlement(user1.getId(), group1.getId(), serviceRequest);

		// then
		List<Participant> participants = participantRepository.findAllBySettlementId(1L);
		Participant agreedParticipant = participants.stream()
			.filter(participant -> participant.getUser().getId().equals(user1.getId()))
			.findFirst().get();

		Assertions.assertThat(participants).hasSize(3);
		Assertions.assertThat(agreedParticipant.isAgreementStatus()).isTrue();
	}

	@Test
	@DisplayName("모든 참여자가 동의할 경우 정산이 이루어진다.")
	void processSettlementTest() {
	    // given
		Settlement settlement = createSettlement(
			"testSettlement",
			30000L,
			LocalDate.now(),
			LocalDate.now(),
			"place",
			group1);
		settlementRepository.save(settlement);

		Participant participant1 = Participant.of(user1.getName(), 10000L, user1, settlement);
		Participant participant2 = Participant.of(user2.getName(), 10000L, user2, settlement);
		Participant participant3 = Participant.of(user3.getName(), 10000L, user3, settlement);
		List<Participant> participants = List.of(participant1, participant2, participant3);
		participantRepository.saveAll(participants);
		participants.forEach(Participant::markAsAgreed);

		// when
		settlementCommandService.processSettlement(user1.getId(), settlement.getId());

	    // then
		Assertions.assertThat(settlement.getSettlementStatus()).isEqualTo(SettlementStatus.SUCCESS);
	}

	@Test
	@DisplayName("모든 참여자가 동의할 경우 정산이 이루어진다.")
	void processSettlementExceptionTest() {
		// given
		Settlement settlement = createSettlement(
			"testSettlement",
			30000L,
			LocalDate.now(),
			LocalDate.now(),
			"place",
			group1);
		settlementRepository.save(settlement);

		Participant participant1 = Participant.of(user1.getName(), 10000L, user1, settlement);
		Participant participant2 = Participant.of(user2.getName(), 10000L, user2, settlement);
		Participant participant3 = Participant.of(user3.getName(), 10000L, user3, settlement);
		participantRepository.saveAll(List.of(participant1, participant2, participant3));
		participant1.markAsAgreed();
		participant2.markAsAgreed();

		// when & then
		Assertions.assertThatThrownBy(() -> settlementCommandService.processSettlement(user1.getId(), settlement.getId()))
			.isInstanceOf(SettlementException.class)
			.extracting("errorCode.message")
			.isEqualTo("참가자의 동의가 부족합니다.");
	}

	private Settlement createSettlement(
		String name,
		Long totalAmount,
		LocalDate groupingAt,
		LocalDate settlementAt,
		String settlementPlace,
		Group group) {
		return Settlement.builder()
			.settlementName(name)
			.totalAmount(totalAmount)
			.settlementStatus(SettlementStatus.OPEN)
			.groupingAt(groupingAt)
			.settlementAt(settlementAt)
			.settlementPlace(settlementPlace)
			.group(group)
			.build();
	}

	private SettlementRegisterServiceRequest getSettlementRegisterServiceRequest() {
		SettlementParticipantServiceRequest request1 = new SettlementParticipantServiceRequest(
			user1.getId(), user1.getName(), 10000L);
		SettlementParticipantServiceRequest request2 = new SettlementParticipantServiceRequest(user2.getId(),
			user2.getName(), 10000L);
		SettlementParticipantServiceRequest request3 = new SettlementParticipantServiceRequest(user3.getId(),
			user3.getName(), 10000L);

		return new SettlementRegisterServiceRequest(
			"name", 30000L, LocalDate.of(2024, 8, 8), LocalDate.of(2024, 8, 8), "place",
			List.of(request1, request2, request3)
		);
	}
}
