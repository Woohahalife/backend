package com.core.backend.user.application.dto;

import com.core.backend.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserSignUpServiceRequest {
	private String email;
	private String password;
	private String name;
	private String phoneNumber;

	public User toEntity() {
		return User.builder()
			.email(email)
			.password(password)
			.name(name)
			.phoneNumber(phoneNumber)
			.build();
	}
}
