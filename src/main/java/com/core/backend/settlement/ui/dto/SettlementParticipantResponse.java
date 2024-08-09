package com.core.backend.settlement.ui.dto;

import com.core.backend.participant.domain.Participant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SettlementParticipantResponse {

    private Long id;
    private String participantName;
    private Long paymentAmount;
    private boolean agreementStatus;

    public static SettlementParticipantResponse from(Participant participant) {
        return new SettlementParticipantResponse(
            participant.getId(),
            participant.getParticipantName(),
            participant.getPaymentAmount(),
            participant.isAgreementStatus()
        );
    }
}
