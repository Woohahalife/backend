package com.core.backend.participant.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.backend.participant.domain.Participant;

public interface JpaParticipantRepository extends JpaRepository<Participant, Long> {

	List<Participant> findAllBySettlementId(Long settlementId);
}
