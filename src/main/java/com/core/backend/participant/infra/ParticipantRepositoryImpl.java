package com.core.backend.participant.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

	private final JpaParticipantRepository repository;

	@Override
	public List<Participant> findAllBySettlementId(final Long settlementId) {
		return repository.findAllBySettlementId(settlementId);
	}

	@Override
	public List<Participant> saveAll(final List<Participant> participants) {
		return repository.saveAll(participants);
	}

	@Override
	public boolean existsBySettlementIdAndUserId(final Long settlementId, final Long userId) {
		return repository.existsBySettlementIdAndUserId(settlementId, userId);
	}
}
