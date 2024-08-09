package com.core.backend.settlement.domain;

import java.time.LocalDate;

import com.core.backend.group.domain.Group;
import com.core.backend.settlement.application.dto.SettlementRegisterServiceRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "settlement")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Settlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "settlement_name", nullable = false)
	private String settlementName;

	@Column(name = "total_amount", nullable = false)
	private Long totalAmount;

	@Enumerated(EnumType.STRING)
	@Column(name = "settlement_status", nullable = false)
	private SettlementStatus settlementStatus;

	@Column(name = "grouping_at", nullable = false)
	private LocalDate groupingAt;

	@Column(name = "settlement_at", nullable = false)
	private LocalDate settlementAt;

	@Column(name = "settlement_place", nullable = false)
	private String settlementPlace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	@Builder
	private Settlement(String settlementName,
		Long totalAmount,
		SettlementStatus settlementStatus,
		LocalDate groupingAt,
		LocalDate settlementAt,
		String settlementPlace,
		Group group) {
		this.settlementName = settlementName;
		this.totalAmount = totalAmount;
		this.settlementStatus = settlementStatus;
		this.groupingAt = groupingAt;
		this.settlementAt = settlementAt;
		this.settlementPlace = settlementPlace;
		this.group = group;
	}

	public static Settlement createSettlement(SettlementRegisterServiceRequest request, Group group) {
		return Settlement.builder()
			.settlementName(request.getSettlementName())
			.totalAmount(request.getTotalAmount())
			.settlementStatus(SettlementStatus.OPEN)
			.groupingAt(request.getGroupingAt())
			.settlementAt(request.getSettlementAt())
			.settlementPlace(request.getSettlementPlace())
			.group(group)
			.build();
	}

	public void statusInProgress() {
		if (this.settlementStatus.equals(SettlementStatus.OPEN)) {
			this.settlementStatus = SettlementStatus.IN_PROGRESS;
		}
	}
}
