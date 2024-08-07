package com.core.backend.auth.ui;

import com.core.backend.auth.application.UserAuthCommandService;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.auth.ui.dto.UserLoginRequest;
import com.core.backend.auth.ui.dto.UserLoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAuthController {

	private final UserAuthCommandService userAuthCommandService;

	@PostMapping("/users/auth/login")
	@Operation(summary = "사용자 로그인 api", description = "사용자 일반 로그인 기능을 제공한다.")
	public ResultResponse<UserLoginResponse> login(@RequestBody UserLoginRequest request) {

		return ResultResponse.success(userAuthCommandService.localLogin(request));
	}

}
