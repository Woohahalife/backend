package com.core.backend.settlement.ui.dto;

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
}
