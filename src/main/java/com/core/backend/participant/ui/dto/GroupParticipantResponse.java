package com.core.backend.participant.ui.dto;

import com.core.backend.participant.domain.Participant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupParticipantResponse {
    private Long id;
	private String participantName;
	private Long paymentAmount;

	public static GroupParticipantResponse from(Participant participant) {
		return new GroupParticipantResponse(
			participant.getId(),
			participant.getParticipantName(),
			participant.getPaymentAmount()
		);
	}
}
