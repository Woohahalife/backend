package com.core.backend.auth.ui.dto;

import com.core.backend.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginResponse {

	private Long userId;
	private String accessToken;

	public static UserLoginResponse of(User user, String accessToken) {
		return new UserLoginResponse(user.getId(), accessToken);
	}
}
