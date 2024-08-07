package com.core.backend.common.mock.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.common.mock.data.GetAllGroupMockData;
import com.core.backend.common.mock.data.GetGroupSettlementMockData;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.group.ui.dto.GroupRegisterRequest;
import com.core.backend.group.ui.dto.GroupSettlementResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class GroupMockController {

	@PostMapping("/mock/groups")
	@Operation(summary = "사용자가 참여한 모임방 조회 api", description = "사용자가 참여한 모임방 목록을 조회한다.")
	public ResultResponse<List<GroupInfoResponse>> getAllGroup(@RequestBody GroupRegisterRequest request) {
		List<GroupInfoResponse> responses = GetAllGroupMockData.mockData();

		return ResultResponse.success(responses);
	}

	@GetMapping("/mock/groups/{groupId}")
	@Operation(summary = "기정산 내역 요약 조회 api", description = "선택한 모임방에 대해 완료된 정산 내역 목록을 조회한다.")
	public ResultResponse<GroupSettlementResponse> getGroupSettlements(@PathVariable Long groupId) {
		Map<Long, GroupSettlementResponse> groupDataMap = GetGroupSettlementMockData.dataMap;
		GroupSettlementResponse response = groupDataMap.get(groupId);

		return ResultResponse.success(response);
	}
}
