package com.core.backend.point.domain;

import com.core.backend.account.exception.AccountException;
import com.core.backend.common.entity.BaseEntity;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "point", nullable = false)
	private Long point;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	public Point(User user) {
		this.point = 0L;
		this.user = user;
	}

	public void increasePoint(Long amount) {
		if (amount == null || amount < 10000) { // TODO : 최소 환전 금액 기준 세우기(일단 1만원으로 설정함)
			throw new AccountException(ErrorCode.ERROR_AMOUNT_TOO_LOW);
		}

		this.point += amount;
	}

	public void decreasePoint(Long amount) {
		if (amount == null || amount < 10000) { // TODO : 최소 환전 금액 기준 세우기(일단 1만원으로 설정함)
			throw new AccountException(ErrorCode.ERROR_AMOUNT_TOO_LOW);
		}

		if(validateSufficientPoint(amount)) {
			throw new AccountException(ErrorCode.INSUFFICIENT_POINT);
		}

		this.point -= amount;
	}

	public void processSettlement(Long amount) {
		if(validateSufficientPoint(amount)) {
			throw new AccountException(ErrorCode.INSUFFICIENT_POINT);
		}

		this.point -= amount;
	}

	public boolean validateSufficientPoint(Long amount) {
		return this.point < amount;
	}
}
