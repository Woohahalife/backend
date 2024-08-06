package com.core.backend.participant.domain;

import com.core.backend.group.domain.Group;
import com.core.backend.settlement.domain.Settlement;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "participant ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "participant_name", nullable = false)
    private String participantName;

    @Column(name = "payment_amount", nullable = false)
    private Long paymentAmount;

    @Column(name = "agreement_status", nullable = false)
    private boolean agreementStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlement;

    public Participant(String participantName, Long paymentAmount) {
        this.participantName = participantName;
        this.paymentAmount = paymentAmount;
        this.agreementStatus = false;
    }
}
