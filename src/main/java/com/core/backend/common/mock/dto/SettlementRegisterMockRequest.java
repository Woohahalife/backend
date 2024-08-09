package com.core.backend.common.mock.dto;

import java.time.LocalDate;
import java.util.List;

import com.core.backend.settlement.ui.dto.SettlementParticipantRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementRegisterMockRequest {

    private Long id; // mockData 저장을 위한 임의 프로퍼티
    private String settlementName;
    private Long totalAmount;
    private LocalDate groupingAt;
    private LocalDate settlementAt;
    private String settlementPlace;
    private List<SettlementParticipantRequest> participants;
}
