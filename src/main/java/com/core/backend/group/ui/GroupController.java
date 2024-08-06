package com.core.backend.group.ui;

import com.core.backend.common.mock.GetAllGroupMockData;
import com.core.backend.common.mock.GetGroupSettlementMockData;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.group.ui.dto.GroupSettlementResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GroupController {

    @GetMapping("/groups")
    @Operation(summary = "사용자가 참여한 모임방 조회 api", description = "사용자가 참여한 모임방 목록을 조회한다.")
    public ResultResponse<List<GroupInfoResponse>> getAllGroup() {

        List<GroupInfoResponse> responses = GetAllGroupMockData.MockData();

        return ResultResponse.success(responses);
    }

    @GetMapping("/groups/{groupsId}")
    @Operation(summary = "기정산 내역 요약 조회 api", description = "선택한 모임방에 대해 완료된 정산 내역 목록을 조회한다.")
    public ResultResponse<GroupSettlementResponse> getGroupSettlements(@PathVariable Long groupsId) {
        Map<Long, GroupSettlementResponse> groupDataMap = GetGroupSettlementMockData.dataMap;
        GroupSettlementResponse response = groupDataMap.get(groupsId);

        return ResultResponse.success(response);
    }
}
