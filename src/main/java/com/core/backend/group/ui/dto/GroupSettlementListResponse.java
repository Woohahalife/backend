package com.core.backend.group.ui.dto;

import java.time.LocalDate;

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
	private int totalPaymentAmount;
	private LocalDate settlementAt;
}
