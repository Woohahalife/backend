package com.core.backend.user.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.user.application.UserCommandService;
import com.core.backend.user.application.UserQueryService;
import com.core.backend.user.ui.dto.UserSignUpRequest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;

	@PostMapping("/users/register")
	@Operation(summary = "사용자 회원가입 api", description = "사용자 회원가입 기능을 제공한다.")
	public ResultResponse<Void> signUp(@RequestBody UserSignUpRequest request) {
		log.info("[UserController -> Called : signUp] 사용자 회원가입 api 동작");

		userCommandService.signUp(request.toService());

		return ResultResponse.success(null);
	}
}
