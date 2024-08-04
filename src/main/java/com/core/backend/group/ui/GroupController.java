package com.core.backend.group.ui;

import com.core.backend.common.mock.GetAllGroupMockData;
import com.core.backend.common.mock.GetGroupSettlementMockData;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.group.ui.dto.GroupSettlementResponse;
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
    public ResultResponse<List<GroupInfoResponse>> getAllGroup() {

        List<GroupInfoResponse> responses = GetAllGroupMockData.MockData();

        return ResultResponse.success(responses);
    }

    @GetMapping("/groups/{groupsId}")
    public ResultResponse<GroupSettlementResponse> getGroupSettlements(@PathVariable Long groupsId) {
        Map<Long, GroupSettlementResponse> groupDataMap = GetGroupSettlementMockData.groupDataMap;
        GroupSettlementResponse response = groupDataMap.get(groupsId);

        return ResultResponse.success(response);
    }
}
