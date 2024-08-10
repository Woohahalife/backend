package com.core.backend.account.ui;

import org.springframework.web.bind.annotation.DeleteMapping;
import com.core.backend.account.ui.dto.AccountResponse;
import org.springframework.web.bind.annotation.*;

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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountCommandService accountCommandService;
	private final AccountQueryService accountQueryService;

	@GetMapping("/accounts")
	@Operation(summary = "계좌정보 조회 api", description = "사용자가 등록한 계좌 목록을 조회한다.")
	public ResultResponse<List<AccountResponse>> getAllAccount(@Authenticated AuthUser authUser) {
		log.info("[AccountController -> Called : getAllAccount] 사용자가 등록한 계좌 목록 조회 api 동작");
		List<AccountResponse> responseList = accountQueryService.getAllAccount(authUser.getUserId());
		return ResultResponse.success(responseList);
	}

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

	@DeleteMapping("/accounts/{accountId}/delete")
	@Operation(summary = "사용자 계좌 삭제 api", description = "사용자가 보유한 계좌를 삭제할 수 있다.")
	public ResultResponse<Void> deleteAccount(@Authenticated AuthUser authUser, @PathVariable Long accountId) {
		accountCommandService.deleteAccount(authUser.getUserId(), accountId);

		return ResultResponse.success();
	}
}
