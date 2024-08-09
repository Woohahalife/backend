package com.core.backend.participant.domain.repository;

import java.util.List;

import com.core.backend.participant.domain.Participant;

public interface ParticipantRepository {

	List<Participant> findAllBySettlementId(Long settlementId);

	List<Participant> saveAll(List<Participant> participants);

	boolean existsBySettlementIdAndUserId(final Long settlementId, final Long userId);
}
