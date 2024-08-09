package com.core.backend.settlement.ui;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.auth.util.Authenticated;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.settlement.application.SettlementCommandService;
import com.core.backend.settlement.application.SettlementQueryService;
import com.core.backend.settlement.application.dto.SettlementRegisterServiceRequest;
import com.core.backend.settlement.ui.dto.RequestedSettlementResponse;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;
import com.core.backend.settlement.ui.dto.SettlementParticipantResponse;
import com.core.backend.settlement.ui.dto.SettlementRegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SettlementController {

	private final SettlementCommandService settlementCommandService;
	private final SettlementQueryService settlementQueryService;

	@GetMapping("/settlements/{settlementId}")
	@Operation(summary = "기정산 내역 상세 조회 api", description = "모임방 별 완료된 정산의 상세 내역을 조회한다.")
	public ResultResponse<SettlementDetailResponse> getGroupSettlementDetail(@Authenticated AuthUser authUser,
		@PathVariable Long settlementId) {
		SettlementDetailResponse response = settlementQueryService.getGroupSettlementDetail(
			authUser.getUserId(),
			settlementId);

		return ResultResponse.success(response);
	}

	@PostMapping("/settlements/{groupId}/request")
	@Operation(summary = "모임방 정산 요청(정산 생성) api", description = "새로운 정산을 요청(생성)한다.")
	public ResultResponse<Void> requestSettlement(@Authenticated AuthUser authUser, @PathVariable Long groupId,
		@RequestBody SettlementRegisterRequest request) {
		settlementCommandService.requestSettlement(authUser.getUserId(), groupId,
			SettlementRegisterServiceRequest.of(request));

		return ResultResponse.success();
	}

	@GetMapping("/settlements/{settlementId}/participants")
	@Operation(summary = "요청한 정산 상황(동의 여부) 조회 api", description = "요청한 정산에 대해 참가자의 동의 상태를 조회한다.")
	public ResultResponse<List<SettlementParticipantResponse>> getSettlementParticipants(
		@Authenticated AuthUser authUser, @PathVariable Long settlementId) {

		List<SettlementParticipantResponse> responses = settlementQueryService
			.getSettlementParticipants(authUser.getUserId(), settlementId);

		return ResultResponse.success(responses);
	}

	@GetMapping("/settlements")
	@Operation(summary = "요청받은 정산 내역 조회 api", description = "요청받은 정산 내역 정보를 조회한다.")
	public ResultResponse<RequestedSettlementResponse> getRequestedSettlement(@Authenticated AuthUser authUser) {
		return Optional.ofNullable(settlementQueryService.getRequestedSettlement(authUser.getUserId()))
			.map(response -> ResultResponse.success(response))
			.orElseGet(() -> ResultResponse.success());
	}
}
