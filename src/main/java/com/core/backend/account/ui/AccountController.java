package com.core.backend.account.ui;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.account.application.AccountCommandService;
import com.core.backend.account.application.AccountQueryService;
import com.core.backend.account.application.dto.AccountRegisterServiceRequest;
import com.core.backend.account.ui.dto.AccountMarkResponse;
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

	@PostMapping("/accounts/register")
	@Operation(summary = "사용자 결제수단 등록 api", description = "사용자가 결제 및 정산에 사용할 계좌를 등록한다.")
	public ResultResponse<Void> registerAccount(@Authenticated AuthUser authUser,
		@RequestBody AccountRegisterRequest request) {

		accountCommandService.registerAccount(authUser.getUserId(),
			AccountRegisterServiceRequest.of(request.getBankName(), request.getAccountNumber()));

		return ResultResponse.success();
	}

	@PutMapping("/accounts/{accountId}/mark")
	@Operation(summary = "사용자 대표계좌 등록 api", description = "사용자가 보유한 계좌를 대표계좌로 등록할 수 있다.")
	public ResultResponse<AccountMarkResponse> setAccountMark(@Authenticated AuthUser authUser, @PathVariable Long accountId) {
		AccountMarkResponse response = accountCommandService.setAccountMark(authUser.getUserId(), accountId);

		return ResultResponse.success(response);
	}

	@PutMapping("/accounts/{accountId}/unmark")
	@Operation(summary = "사용자 대표계좌 등록 해제 api", description = "사용자가 보유한 대표 계좌를 해제할 수 있다.")
	public ResultResponse<AccountMarkResponse> unsetAccountMark(@Authenticated AuthUser authUser, @PathVariable Long accountId) {
		AccountMarkResponse response = accountCommandService.unsetAccountMark(authUser.getUserId(), accountId);

		return ResultResponse.success(response);
	}
}
