package com.core.backend.settlement.application.dto;

import com.core.backend.settlement.ui.dto.SettlementParticipantRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettlementParticipantServiceRequest {

	private Long id;
	private String participantName;
	private Long paymentAmount;

	public static SettlementParticipantServiceRequest from(SettlementParticipantRequest request) {
		return new SettlementParticipantServiceRequest(
			request.getId(),
			request.getParticipantName(),
			request.getPaymentAmount()
		);
	}
}
