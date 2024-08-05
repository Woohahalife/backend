package com.core.backend.group.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupSettlementListResponse {  
  
    private Long id;  
    private String settlementName;
    private int totalPaymentAmount;
    private LocalDate settlementAt;
}