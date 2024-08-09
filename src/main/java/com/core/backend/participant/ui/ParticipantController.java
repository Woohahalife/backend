package com.core.backend.participant.ui;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.auth.util.Authenticated;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.participant.application.ParticipantCommandService;
import com.core.backend.participant.application.ParticipantQueryService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

	private final ParticipantCommandService participantCommandService;
	private final ParticipantQueryService participantQueryService;

	@PutMapping("/participant/{participantId}")
	@Operation(summary = "요청받은 정산 내역 동의 api", description = "요청받은 정산에 동의할 수 있는 기능을 제공한다.")
	public ResultResponse<Void> agreementSettlement(@Authenticated AuthUser authUser, @PathVariable Long participantId) {
		participantCommandService.agreementSettlement(authUser.getUserId(), participantId);

		return ResultResponse.success();
	}
}
