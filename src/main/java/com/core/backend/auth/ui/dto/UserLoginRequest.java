package com.core.backend.auth.ui.dto;

import lombok.Getter;

@Getter
public class UserLoginRequest {

	private String email;
	private String password;
}
