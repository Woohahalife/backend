package com.core.backend.participant.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantCommandService {

	private final ParticipantRepository participantRepository;

	public void agreementSettlement(Long userId, Long participantId) {
		participantRepository.findByIdAndUserId(participantId, userId).markAsAgreed();
	}
}
