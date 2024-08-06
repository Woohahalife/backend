package com.core.backend.settlement.ui;

import com.core.backend.common.mock.GetGroupSettlementDetailMockData;
import com.core.backend.common.mock.RequestSettlementMockData;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;
import com.core.backend.settlement.ui.dto.SettlementParticipantResponse;
import com.core.backend.settlement.ui.dto.SettlementRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SettlementController {

    @GetMapping("/settlements/{settlementId}")
    @Operation(summary = "기정산 내역 상세 조회 api", description = "모임방 별로 완료 내역을 상세 조회한다.")
    public ResultResponse<SettlementDetailResponse> getGroupSettlementDetail(@PathVariable Long settlementId) {

        Map<Long, SettlementDetailResponse> dataMap = GetGroupSettlementDetailMockData.dataMap;
        SettlementDetailResponse response = dataMap.get(settlementId);

        return ResultResponse.success(response);
    }

    @PostMapping("/settlements/request")
    @Operation(summary = "모임방 정산 요청(정산 생성) api", description = "새로운 정산을 요청(생성)한다.")
    public ResultResponse<Void> requestSettlement(@RequestBody SettlementRegisterRequest request) {

        RequestSettlementMockData.saveSettlement(request);

        return ResultResponse.success(null);
    }

    @GetMapping("/settlements/{settlementId}/participants")
    @Operation(summary = "요청한 정산 상황(동의 여부) 조회 api", description = "요청한 정산에 대해 참가자의 동의 상태를 조회한다.")
    public ResultResponse<List<SettlementParticipantResponse>> getSettlementParticipants(@PathVariable Long settlementId) {
        List<SettlementParticipantResponse> response = RequestSettlementMockData.getParticipantsBySettlement(settlementId);

        return ResultResponse.success(response);
    }
}