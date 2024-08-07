package com.core.backend.account.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.account.application.AccountCommandService;
import com.core.backend.account.application.AccountQueryService;
import com.core.backend.account.application.dto.AccountRegisterServiceRequest;
import com.core.backend.account.ui.dto.AccountRegisterRequest;
import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.auth.util.Authenticated;
import com.core.backend.common.repsonse.ResultResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountCommandService accountCommandService;
	private final AccountQueryService accountQueryService;

	@PostMapping("/api/accounts/register")
	@Operation(summary = "사용자 결제수단 등록 api", description = "사용자가 결제 및 정산에 사용할 계좌를 등록한다.")
	public ResultResponse<Void> registerAccount(@Authenticated AuthUser authUser,
		@RequestBody AccountRegisterRequest request) {

		accountCommandService.registerAccount(authUser.getUserId(),
			AccountRegisterServiceRequest.ofCreate(request.getBankName(), request.getAccountNumber()));

		return ResultResponse.success();
	}
}
