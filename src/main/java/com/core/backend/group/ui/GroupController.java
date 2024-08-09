package com.core.backend.group.ui;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.auth.util.Authenticated;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.group.application.GroupCommandService;
import com.core.backend.group.application.GroupQueryService;
import com.core.backend.group.application.dto.GroupEntranceServiceRequest;
import com.core.backend.group.application.dto.GroupRegisterServiceRequest;
import com.core.backend.group.ui.dto.CreateGroupResponse;
import com.core.backend.group.ui.dto.GroupEntranceRequest;
import com.core.backend.group.ui.dto.GroupEntranceResponse;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.group.ui.dto.GroupInviteResponse;
import com.core.backend.group.ui.dto.GroupRegisterRequest;
import com.core.backend.group.ui.dto.GroupSettlementResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GroupController {

	private final GroupQueryService groupQueryService;
	private final GroupCommandService groupCommandService;

	@PostMapping("/groups")
	@Operation(summary = "사용자가 참여할 수있는 모임방 생성 api", description = "사용자가 참여할 수 있는 모임방을 생성한다.")
	public ResultResponse<CreateGroupResponse> createGroup(@Authenticated AuthUser authUser,
		@RequestBody GroupRegisterRequest request) {
		log.info("[GroupController -> Called : registerGroup] 사용자가 참여할 수있는 모임방 생성 api 동작");
		CreateGroupResponse response =
			groupCommandService.createGroup(authUser.getUserId(), GroupRegisterServiceRequest.from(request.getGroupName()));

		return ResultResponse.success();
	}

	@GetMapping("/groups")
	@Operation(summary = "사용자가 참여한 모임방 조회 api", description = "사용자가 참여한 모임방 목록을 조회한다.")
	public ResultResponse<List<GroupInfoResponse>> getAllGroup(@Authenticated AuthUser authUser) {
		log.info("[GroupController -> Called : getAllGroup] 사용자가 참여한 모임방 조회 api 동작");
		List<GroupInfoResponse> responseList = groupQueryService.getAllGroup(authUser.getUserId());

		return ResultResponse.success(responseList);
	}

	@GetMapping("/groups/{groupId}")
	@Operation(summary = "기정산 내역 요약 조회 api(모임방 상세 조회)", description = "선택한 모임방에 대해 완료된 정산 내역 목록을 조회한다.")
	public ResultResponse<GroupSettlementResponse> getGroupSettlements(@Authenticated AuthUser authUser,
		@PathVariable Long groupId) {
		GroupSettlementResponse response = groupQueryService.getGroupSettlements(authUser.getUserId(), groupId);

		return ResultResponse.success(response);
	}

	@PostMapping("/groups/{groupId}/invite")
	@Operation(summary = "모임 참여자 초대 api", description = "초대코드를 생성해 모임방에 참여할 수 있는 기능을 제공한다.")
	public ResultResponse<GroupInviteResponse> generateInviteCode(@Authenticated AuthUser authUser,
		@PathVariable Long groupId) {

		return ResultResponse.success(groupCommandService.generateInviteCode(authUser.getUserId(), groupId));
	}

	@PostMapping("/groups/entrance")
	@Operation(summary = "모임방 입장 api", description = "초대코드 입력시 모임방에 입장하는 기능을 제공한다.")
	public ResultResponse<GroupEntranceResponse> groupEntrance(@Authenticated AuthUser authUser,
		@RequestBody GroupEntranceRequest request) {
		GroupEntranceResponse response = groupCommandService.groupEntrance(authUser.getUserId(),
			GroupEntranceServiceRequest.from(request.getInvitationCode()));

		return ResultResponse.success(response);
	}
}
