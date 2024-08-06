package com.core.backend.settlement.domain;

import com.core.backend.group.domain.Group;
import com.core.backend.point.domain.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "settlement")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "settlement_name", nullable = false)
    private String settlementName;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Column(name = "settlement_status", nullable = false)
    private SettlementStatus settlementStatus;

    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "grouping_at", nullable = false)
    private LocalDate groupingAt;

    @Column(name = "settlement_at", nullable = false)
    private LocalDate settlementAt;

    @Column(name = "settlement_place", nullable = false)
    private String settlementPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Builder
    private Settlement(String settlementName,
                      Long totalAmount,
                      SettlementStatus settlementStatus,
                      String qrcode,
                      LocalDate groupingAt,
                      LocalDate settlementAt,
                      String settlementPlace) {
        this.settlementName = settlementName;
        this.totalAmount = totalAmount;
        this.settlementStatus = settlementStatus;
        this.qrcode = qrcode;
        this.groupingAt = groupingAt;
        this.settlementAt = settlementAt;
        this.settlementPlace = settlementPlace;
    }
}
