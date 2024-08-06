package com.core.backend.common.mock;

import com.core.backend.participant.ui.dto.GroupParticipantResponse;
import com.core.backend.settlement.ui.dto.SettlementDetailResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetGroupSettlementDetailMockData {

    public static final Map<Long, SettlementDetailResponse> dataMap = new HashMap<>();

    static {
        dataMap.put(1L, getSettlementDetailResponse1ForGroup1());
        dataMap.put(2L, getSettlementDetailResponse2ForGroup1());
        dataMap.put(3L, getSettlementDetailResponse3ForGroup1());
        dataMap.put(4L, getSettlementDetailResponse1ForGroup2());
        dataMap.put(5L, getSettlementDetailResponse2ForGroup2());
        dataMap.put(6L, getSettlementDetailResponse1ForGroup3());
        dataMap.put(7L, getSettlementDetailResponse2ForGroup3());
    }

    public static SettlementDetailResponse getSettlementDetailResponse1ForGroup1() {
        int totalAmount = 77000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses1(totalAmount);

        return new SettlementDetailResponse(
                1L,
                "1차 회식",
                totalAmount,
                LocalDate.of(2024, 5, 12),
                LocalDate.of(2024, 5, 12),
                "서울 강남",
                participationResponses
        );
    }

    public static SettlementDetailResponse getSettlementDetailResponse2ForGroup1() {
        int totalAmount = 80000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses1(totalAmount);

        return new SettlementDetailResponse(
                2L,
                "2차 회식",
                totalAmount,
                LocalDate.of(2024, 5, 12),
                LocalDate.of(2024, 5, 12),
                "서울 강남",
                participationResponses
        );
    }

    public static SettlementDetailResponse getSettlementDetailResponse3ForGroup1() {
        int totalAmount = 90000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses1(totalAmount);

        return new SettlementDetailResponse(
                3L,
                "3차 회식",
                totalAmount,
                LocalDate.of(2024, 5, 12),
                LocalDate.of(2024, 5, 12),
                "서울 강남",
                participationResponses
        );
    }

    public static SettlementDetailResponse getSettlementDetailResponse1ForGroup2() {
        int totalAmount = 60000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses2(totalAmount);

        return new SettlementDetailResponse(
                4L,
                "첫 모임 1차",
                totalAmount,
                LocalDate.of(2024, 6, 23),
                LocalDate.of(2024, 6, 23),
                "서울 역삼",
                participationResponses
        );
    }

    public static SettlementDetailResponse getSettlementDetailResponse2ForGroup2() {
        int totalAmount = 70000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses2(totalAmount);

        return new SettlementDetailResponse(
                5L,
                "첫 모임 2차",
                totalAmount,
                LocalDate.of(2024, 6, 23),
                LocalDate.of(2024, 6, 23),
                "서울 역삼",
                participationResponses
        );
    }

    public static SettlementDetailResponse getSettlementDetailResponse1ForGroup3() {
        int totalAmount = 50000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses3(totalAmount);

        return new SettlementDetailResponse(
                6L,
                "팀 회의",
                totalAmount,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 7, 1),
                "회사 회의실",
                participationResponses
        );
    }

    public static SettlementDetailResponse getSettlementDetailResponse2ForGroup3() {
        int totalAmount = 60000;
        List<GroupParticipantResponse> participationResponses = getGroupParticipantResponses3(totalAmount);

        return new SettlementDetailResponse(
                7L,
                "팀 디너",
                totalAmount,
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 7, 1),
                "삼성역 코엑스",
                participationResponses
        );
    }

    private static List<GroupParticipantResponse> getGroupParticipantResponses1(int totalAmount) {
        return calculateParticipantAmounts(
                List.of(
                        new GroupParticipantResponse(1L, "민선", 0),
                        new GroupParticipantResponse(2L, "나은", 0),
                        new GroupParticipantResponse(3L, "해성", 0),
                        new GroupParticipantResponse(4L, "한비", 0),
                        new GroupParticipantResponse(5L, "나영", 0),
                        new GroupParticipantResponse(6L, "건", 0)
                ), totalAmount
        );
    }

    private static List<GroupParticipantResponse> getGroupParticipantResponses2(int totalAmount) {
        return calculateParticipantAmounts(
                List.of(
                        new GroupParticipantResponse(1L, "민선", 0),
                        new GroupParticipantResponse(8L, "영희", 0),
                        new GroupParticipantResponse(9L, "길동", 0)
                ), totalAmount
        );
    }

    private static List<GroupParticipantResponse> getGroupParticipantResponses3(int totalAmount) {
        return calculateParticipantAmounts(
                List.of(
                        new GroupParticipantResponse(1L, "민선", 0),
                        new GroupParticipantResponse(11L, "정훈", 0),
                        new GroupParticipantResponse(12L, "상현", 0)
                ), totalAmount
        );
    }


    private static List<GroupParticipantResponse> calculateParticipantAmounts(List<GroupParticipantResponse> members, int totalAmount) {
        int numberOfMembers = members.size();
        int baseAmount = totalAmount / numberOfMembers;
        int remainder = totalAmount % numberOfMembers;

        List<GroupParticipantResponse> responses = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            GroupParticipantResponse member = members.get(i);
            int amount = baseAmount + (i < remainder ? 1 : 0);
            responses.add(new GroupParticipantResponse(member.getId(), member.getName(), amount));
        }
        return responses;
    }


}
