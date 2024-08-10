package com.core.backend.participant.domain;

import org.hibernate.annotations.SQLRestriction;

import com.core.backend.common.entity.BaseEntity;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "participant", uniqueConstraints = @UniqueConstraint(columnNames = {"settlement_id", "user_id"}))
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Participant(String participantName, Long paymentAmount, User user, Settlement settlement) {
		this.participantName = participantName;
		this.paymentAmount = paymentAmount;
		this.agreementStatus = false;
		this.user = user;
		this.settlement = settlement;
	}

	public static Participant of(String participantName, Long paymentAmount, User user, Settlement settlement) {
		return new Participant(
			participantName,
			paymentAmount,
			user,
			settlement
		);
	}

	public void markAsAgreed() {
		if (!this.agreementStatus) {
			this.agreementStatus = true;
		}
	}
}
