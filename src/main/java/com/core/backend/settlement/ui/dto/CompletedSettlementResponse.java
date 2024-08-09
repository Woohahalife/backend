package com.core.backend.settlement.ui.dto;

import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CompletedSettlementResponse {
	private Long settlementId;
	private SettlementStatus settlementStatus;

	public static CompletedSettlementResponse from(Settlement settlement) {
		return new CompletedSettlementResponse(settlement.getId(), settlement.getSettlementStatus());
	}
}
