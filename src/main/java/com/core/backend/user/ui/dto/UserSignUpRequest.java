package com.core.backend.user.ui.dto;

import com.core.backend.user.application.dto.UserSignUpServiceRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignUpRequest {

	private String email;
	private String password;
	private String name;
	private String phoneNumber;

	public UserSignUpServiceRequest toService() {
		return new UserSignUpServiceRequest(
			this.email,
			this.password,
			this.name,
			this.phoneNumber
		);
	}
}
