package com.core.backend.group.domain;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvitationCode {

	private String code;

	protected InvitationCode(String code) {
		this.code = code;
	}

	public void generateRandomCode() {
		int min = 1;
		int max = 99999;

		int randomNumber = new Random().nextInt(max + 1) + min;
		this.code = String.format("%05d", randomNumber);
	}
}
