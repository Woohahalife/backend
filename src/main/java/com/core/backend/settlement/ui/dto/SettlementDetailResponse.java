package com.core.backend.settlement.ui.dto;

import com.core.backend.participation.ui.dto.GroupParticipationResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SettlementDetailResponse {
    private Long id; // 정산id
    private String settlementName; // 정산 내용
    private int totalAmount; // 정산 총액
    private LocalDate groupingAt; // 모임 일자
    private LocalDate settlementAt; // 정산 완료 일자
    private String settlementPlace; // 모임 장소
    private List<GroupParticipationResponse> participaints;
}
