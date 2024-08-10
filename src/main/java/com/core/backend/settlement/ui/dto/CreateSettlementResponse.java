package com.core.backend.settlement.ui.dto;

import com.core.backend.settlement.domain.Settlement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateSettlementResponse {

	private Long groupId;

	public static CreateSettlementResponse from(Settlement settlement) {
		return new CreateSettlementResponse(settlement.getId());
	}
}
