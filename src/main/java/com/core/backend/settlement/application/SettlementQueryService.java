package com.core.backend.settlement.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.settlement.domain.SettlementRepository;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SettlementQueryService {

	private final SettlementRepository settlementRepository;

	public SettlementDetailResponse getGroupSettlementDetail(Long userId, Long settlementId) {
		return null;
	}
}
