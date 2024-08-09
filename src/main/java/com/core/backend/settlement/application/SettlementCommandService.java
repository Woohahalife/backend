package com.core.backend.settlement.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;
import com.core.backend.settlement.application.dto.SettlementParticipantServiceRequest;
import com.core.backend.settlement.application.dto.SettlementRegisterServiceRequest;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementRepository;
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
	public void requestSettlement(Long userId, Long groupId, SettlementRegisterServiceRequest request) {
		Group group = groupRepository.findById(groupId);

		Map<Long, SettlementParticipantServiceRequest> dataMap = request.getParticipants()
			.stream()
			.collect(Collectors.toMap(
				SettlementParticipantServiceRequest::getId,
				Function.identity()
			));

		Settlement settlement = Settlement.createSettlement(request, group);
		settlementRepository.save(settlement);

		List<Participant> participants = userRepository.findAllById(new ArrayList<>(dataMap.keySet()))
			.stream()
			.map(user -> {
				SettlementParticipantServiceRequest data = dataMap.get(user.getId());
				return Participant.of(data.getParticipantName(), data.getPaymentAmount(), user, settlement);
			})
			.toList();

		participants.stream()
			.filter(participant -> participant.getUser().getId().equals(userId))
			.forEach(Participant::markAsAgreed);

		participantRepository.saveAll(participants);

		// TODO : Settlement OPEN -> IN_PROGRESS 상태 변경 event 처리 필요
	}
}
