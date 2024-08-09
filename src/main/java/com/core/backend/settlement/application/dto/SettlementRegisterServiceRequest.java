package com.core.backend.settlement.application.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.core.backend.settlement.ui.dto.SettlementParticipantRequest;
import com.core.backend.settlement.ui.dto.SettlementRegisterRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SettlementRegisterServiceRequest {
	private String settlementName;
	private Long totalAmount;
	private LocalDate groupingAt;
	private LocalDate settlementAt;
	private String settlementPlace;
	private List<SettlementParticipantServiceRequest> participants;

	public static SettlementRegisterServiceRequest of(SettlementRegisterRequest request) {
		return new SettlementRegisterServiceRequest(request.getSettlementName(), request.getTotalAmount(),
			request.getGroupingAt(), request.getSettlementAt(), request.getSettlementPlace(),
			toService(request.getParticipants()));
	}

	private static List<SettlementParticipantServiceRequest> toService(
		List<SettlementParticipantRequest> participants) {
		return participants.stream()
			.map(SettlementParticipantServiceRequest::from)
			.collect(Collectors.toList());
	}

}
