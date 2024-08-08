package com.core.backend.group.ui.dto;

import java.time.LocalDate;

import com.core.backend.settlement.domain.Settlement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupSettlementListResponse {

	private Long id;
	private String settlementName;
	private Long totalPaymentAmount;
	private LocalDate settlementAt;

	public static GroupSettlementListResponse fromSettlement(Settlement settlement) {
		return new GroupSettlementListResponse(settlement.getId(),
			settlement.getSettlementName(),
			settlement.getTotalAmount(),
			settlement.getSettlementAt());
	}
}
