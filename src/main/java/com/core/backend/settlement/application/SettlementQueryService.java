package com.core.backend.settlement.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementRepository;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SettlementQueryService {

	private final SettlementRepository settlementRepository;
	private final ParticipantRepository participantRepository;

	public SettlementDetailResponse getGroupSettlementDetail(Long userId, Long settlementId) {
		Settlement settlement = settlementRepository.findById(settlementId);
		List<Participant> participants = participantRepository.findAllBySettlementId(settlementId);

		return SettlementDetailResponse.of(settlement, participants);
	}
}
