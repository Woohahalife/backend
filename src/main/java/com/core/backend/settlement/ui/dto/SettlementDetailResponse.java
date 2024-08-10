package com.core.backend.settlement.ui.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.ui.dto.GroupParticipantResponse;
import com.core.backend.settlement.domain.Settlement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SettlementDetailResponse {
    private Long id; // 정산id
    private String settlementName; // 정산 내용
    private Long totalAmount; // 정산 총액
    private LocalDate groupingAt; // 모임 일자
    private LocalDate settlementAt; // 정산 완료 일자
    private String settlementPlace; // 모임 장소
    private List<GroupParticipantResponse> participants;

    public static SettlementDetailResponse of(Settlement settlement, List<Participant> participants) {
        return SettlementDetailResponse.builder()
            .id(settlement.getId())
            .settlementName(settlement.getSettlementName())
            .totalAmount(settlement.getTotalAmount())
            .groupingAt(settlement.getGroupingAt())
            .settlementAt(settlement.getSettlementAt())
            .settlementPlace(settlement.getSettlementPlace())
            .participants(
                participants.stream()
                .map(GroupParticipantResponse::from)
                .collect(Collectors.toList())
            )
            .build();
    }
}
