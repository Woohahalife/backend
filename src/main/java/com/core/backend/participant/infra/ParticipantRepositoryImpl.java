package com.core.backend.participant.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;
import com.core.backend.participant.exception.ParticipantException;

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

	@Override
	public List<Participant> findAllByUserId(final Long userId) {
		return repository.findAllByUserId(userId);
	}

	@Override
	public List<Participant> findByUserIdAndAgreementStatusFalse(final Long userId) {
		return repository.findByUserIdAndAgreementStatusFalse(userId);
	}

	@Override
	public Participant findByIdAndUserId(Long participantId, Long userId) {
		return repository.findByIdAndUserId(participantId, userId)
			.orElseThrow(() -> new ParticipantException(ErrorCode.NOT_FOUND_SETTLEMENT_PARTICIPANT));
	}
}
