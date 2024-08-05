package com.core.backend.settlement.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SettlementRegisterRequest {

    private Long id; // mockData 저장을 위한 임의 프로퍼티
    private String settlementName;
    private int totalAmount;
    private LocalDate groupingAt;
    private LocalDate settlementAt;
    private String settlementPlace;
    private List<SettlementParticipantRequest> participants;
}
