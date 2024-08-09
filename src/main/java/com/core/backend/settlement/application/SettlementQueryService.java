package com.core.backend.settlement.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;
import com.core.backend.participant.exception.ParticipantException;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementRepository;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;
import com.core.backend.settlement.ui.dto.SettlementParticipantResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SettlementQueryService {

	private final SettlementRepository settlementRepository;
	private final ParticipantRepository participantRepository;

	public SettlementDetailResponse getGroupSettlementDetail(Long userId, Long settlementId) {
		validateExistParticipant(userId, settlementId);

		Settlement settlement = settlementRepository.findById(settlementId);
		List<Participant> participants = participantRepository.findAllBySettlementId(settlementId);

		return SettlementDetailResponse.of(settlement, participants);
	}

	public List<SettlementParticipantResponse> getSettlementParticipants(Long userId, Long settlementId) {
		validateExistParticipant(userId, settlementId);

		List<Participant> participants = participantRepository.findAllBySettlementId(settlementId);

		return participants.stream()
			.map(SettlementParticipantResponse::from)
			.collect(Collectors.toList());
	}

	private void validateExistParticipant(Long userId, Long settlementId) {
		if(!participantRepository.existsBySettlementIdAndUserId(userId, settlementId)) {
			throw new ParticipantException(ErrorCode.NOT_FOUND_SETTLEMENT_PARTICIPANT);
		}
	}
}
