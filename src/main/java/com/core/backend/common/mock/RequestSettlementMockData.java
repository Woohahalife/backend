package com.core.backend.common.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.core.backend.settlement.ui.dto.SettlementParticipantRequest;
import com.core.backend.settlement.ui.dto.SettlementParticipantResponse;
import com.core.backend.settlement.ui.dto.SettlementRegisterRequest;

public class RequestSettlementMockData {

	private static final Map<Long, SettlementRegisterRequest> settlements = new HashMap<>();
	private static final Map<Long, SettlementParticipantRequest> participantsMap = new HashMap<>();
	private static final Map<Long, List<Long>> settlementParticipantsMap = new HashMap<>();

	public static void saveSettlement(SettlementRegisterRequest request) {
		settlements.put(request.getId(), request);

		List<Long> participantIds = new ArrayList<>();

		for (SettlementParticipantRequest participant : request.getParticipants()) {
			participantsMap.put(participant.getId(), participant);
			participantIds.add(participant.getId());
		}

		settlementParticipantsMap.put(request.getId(), participantIds);
	}

	public static List<SettlementParticipantResponse> getParticipantsBySettlement(Long settlementId) {
		List<Long> participantIds = settlementParticipantsMap.get(settlementId);

		if (participantIds == null) {
			return Collections.emptyList();
		}

		return participantIds.stream()
			.map(participantId -> participantsMap.get(participantId))
			.filter(Objects::nonNull)
			.map(participant -> new SettlementParticipantResponse(
				participant.getId(),
				participant.getParticipantName(),
				participant.getPaymentAmount(),
				true
			))
			.collect(Collectors.toList());
	}

}
