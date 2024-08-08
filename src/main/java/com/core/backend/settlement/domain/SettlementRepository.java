package com.core.backend.settlement.domain;

import java.util.List;

public interface SettlementRepository {

	List<Settlement> findByGroupIdAndStatus(final Long groupId, final SettlementStatus status);
}
