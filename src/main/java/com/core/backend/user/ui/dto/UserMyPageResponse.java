package com.core.backend.user.ui.dto;

import com.core.backend.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserMyPageResponse {

	private Long id;
	private String name;
	private String phoneNumber;

	public static UserMyPageResponse from(User user) {
		return new UserMyPageResponse(user.getId(), user.getName(), user.getPhoneNumber());
	}
}
