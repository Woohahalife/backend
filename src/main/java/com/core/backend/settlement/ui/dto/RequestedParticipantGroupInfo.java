package com.core.backend.settlement.ui.dto;

import com.core.backend.participant.domain.Participant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RequestedParticipantGroupInfo {
	private Long id;
	private String participantName;
	private Long paymentAmount;

	public static RequestedParticipantGroupInfo of(Participant participant) {
		return new RequestedParticipantGroupInfo(
			participant.getId(),
			participant.getParticipantName(),
			participant.getPaymentAmount());
	}
}
