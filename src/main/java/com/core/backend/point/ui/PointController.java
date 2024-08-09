package com.core.backend.point.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.auth.util.Authenticated;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.point.application.PointCommandService;
import com.core.backend.point.application.PointQueryService;
import com.core.backend.point.application.dto.PointConversionServiceRequest;
import com.core.backend.point.ui.dto.PointConversionRequest;
import com.core.backend.point.ui.dto.PointConversionResponse;
import com.core.backend.point.ui.dto.PointInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PointController {

	private final PointCommandService pointCommandService;
	private final PointQueryService pointQueryService;

	@GetMapping("/points")
	@Operation(summary = "사용자 보유 포인트 조회 api", description = "사용자가 보유한 포인트를 조회할 수 있다.")
	public ResultResponse<PointInfoResponse> getPointInfo(@Authenticated AuthUser authUser) {

		return ResultResponse.success(pointQueryService.getPointInfo(authUser.getUserId()));
	}

	@PostMapping("/points")
	@Operation(summary = "사용자 계좌로부터 포인트 환전 기능 api", description = "사용자의 계좌 잔액을 포인트로 환전할 수 있다.")
	public ResultResponse<PointConversionResponse> convertBalanceToPoints(
		@Authenticated AuthUser authUser,
		@RequestBody PointConversionRequest request) {

		PointConversionResponse response = pointCommandService.convertBalanceToPoints(
			authUser.getUserId(),
			PointConversionServiceRequest.from(request.getAmount())
		);

		return ResultResponse.success(response);
	}
}
