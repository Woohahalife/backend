package com.core.backend.settlement.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettlementParticipantRequest {

    private Long id;
    private String participantName;
    private Long paymentAmount;
}
