package com.core.backend.settlement.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementRepository;
import com.core.backend.settlement.domain.SettlementStatus;
import com.core.backend.settlement.exception.SettlementException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SettlementRepositoryImpl implements SettlementRepository {

	private final JpaSettlementRepository repository;

	@Override
	public List<Settlement> findByGroupIdAndStatus(final Long groupId, final SettlementStatus status) {
		return repository.findAllByGroupIdAndSettlementStatus(groupId, status);
	}

	@Override
	public Settlement findById(Long settlementId) {
		return repository.findById(settlementId)
			.orElseThrow(() -> new SettlementException(ErrorCode.NOT_FOUND_SETTLEMENT));
	}
}
