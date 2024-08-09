package com.core.backend.settlement.domain;

import java.util.List;

public interface SettlementRepository {

	List<Settlement> findAllByGroupIdAndStatus(final Long groupId, final SettlementStatus status);

	Settlement findById(final Long settlementId);

	Settlement save(Settlement settlement);

}
