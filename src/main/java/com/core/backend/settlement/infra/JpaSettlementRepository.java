package com.core.backend.settlement.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementStatus;

public interface JpaSettlementRepository extends JpaRepository<Settlement, Long> {

	List<Settlement> findAllByGroupIdAndSettlementStatus(final Long groupId, final SettlementStatus status);
}
