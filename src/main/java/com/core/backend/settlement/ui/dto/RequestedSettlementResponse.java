package com.core.backend.settlement.ui.dto;

import java.time.LocalDate;

import com.core.backend.group.domain.Group;
import com.core.backend.participant.domain.Participant;
import com.core.backend.settlement.domain.Settlement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RequestedSettlementResponse {

	private Long settlementId;
	private String settlementName;
	private LocalDate groupingAt;
	private Long totalAmount;
	private RequestedSettlementGroupInfo group;
	private RequestedParticipantGroupInfo participant;

	public static RequestedSettlementResponse empty() {
		return new RequestedSettlementResponse();
	}

	public static RequestedSettlementResponse of(
		Settlement settlement,
		Participant participant,
		Group group
	) {
		return new RequestedSettlementResponse(
			settlement.getId(),
			settlement.getSettlementName(),
			settlement.getGroupingAt(),
			settlement.getTotalAmount(),
			RequestedSettlementGroupInfo.of(group),
			RequestedParticipantGroupInfo.of(participant)
		);
	}
}
