package com.core.backend.settlement.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;
import com.core.backend.settlement.application.dto.SettlementParticipantServiceRequest;
import com.core.backend.settlement.application.dto.SettlementRegisterServiceRequest;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementRepository;
import com.core.backend.settlement.exception.SettlementException;
import com.core.backend.settlement.ui.dto.CompletedSettlementResponse;
import com.core.backend.settlement.ui.dto.CreateSettlementResponse;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SettlementCommandService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;
	private final SettlementRepository settlementRepository;
	private final ParticipantRepository participantRepository;

	// TODO : 리팩토링 우선순위
	public CreateSettlementResponse requestSettlement(Long userId, Long groupId, SettlementRegisterServiceRequest request) {
		Group group = groupRepository.findById(groupId);

		Map<Long, SettlementParticipantServiceRequest> dataMap = getDataMap(request);

		Settlement settlement = Settlement.createSettlement(request, group);
		settlementRepository.save(settlement);

		List<Participant> participants = getParticipantList(dataMap, settlement);

		participants.stream()
			.filter(participant -> participant.getUser().getId().equals(userId))
			.forEach(Participant::markAsAgreed);

		participantRepository.saveAll(participants);

		return CreateSettlementResponse.from(settlement);
	}

	private Map<Long, SettlementParticipantServiceRequest> getDataMap(SettlementRegisterServiceRequest request) {
		return request.getParticipants()
			.stream()
			.collect(Collectors.toMap(
				SettlementParticipantServiceRequest::getId,
				Function.identity()
			));
	}

	private List<Participant> getParticipantList(Map<Long, SettlementParticipantServiceRequest> dataMap,
		Settlement settlement) {
		return userRepository.findAllById(new ArrayList<>(dataMap.keySet()))
			.stream()
			.map(user -> {
				SettlementParticipantServiceRequest data = dataMap.get(user.getId());
				return Participant.of(data.getParticipantName(), data.getPaymentAmount(), user, settlement);
			})
			.toList();
	}

	public CompletedSettlementResponse processSettlement(Long userId, Long settlementId) {
		//정산 조회
		Settlement settlement = settlementRepository.findById(settlementId);
		// 정산 참가자 추출
		List<Participant> participants = participantRepository.findAllBySettlementId(settlementId);
		validateAllParticipantAgree(participants);

		// 각 참가자 point를 결제 금액만큼 감소
		participants
			.forEach(participant -> {
				Long paymentAmount = participant.getPaymentAmount();
				participant.getUser().getPoint().processSettlement(paymentAmount);
				// TODO : 포인트가 부족한 경우 계좌로부터 환전 후 재결제 요청(현재는 예외만 던지도록 되어있음)
			});

		settlement.completeSettlement();

		return CompletedSettlementResponse.from(settlement);
	}

	private void validateAllParticipantAgree(List<Participant> participants) {
		if (!participants.stream().allMatch(Participant::isAgreementStatus)) {
			throw new SettlementException(ErrorCode.PARTICIPANT_AGREEMENT_MISSING);
		}
	}
}
