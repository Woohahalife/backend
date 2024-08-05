package com.core.backend.settlement.ui;

import com.core.backend.common.mock.GetGroupSettlementDetailMockData;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SettlementController {

    @GetMapping("/settlements/{settlementId}")
    public ResultResponse<SettlementDetailResponse> getGroupSettlementDetail(@PathVariable Long settlementId) {

        Map<Long, SettlementDetailResponse> dataMap = GetGroupSettlementDetailMockData.dataMap;
        SettlementDetailResponse response = dataMap.get(settlementId);

        return ResultResponse.success(response);
    }
}
