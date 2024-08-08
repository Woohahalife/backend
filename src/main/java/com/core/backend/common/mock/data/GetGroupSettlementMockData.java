package com.core.backend.common.mock.data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.core.backend.group.ui.dto.GroupMemberResponse;
import com.core.backend.group.ui.dto.GroupSettlementListResponse;
import com.core.backend.group.ui.dto.GroupSettlementResponse;

public class GetGroupSettlementMockData {

	public static final Map<Long, GroupSettlementResponse> dataMap = new HashMap<>();

	static {
		dataMap.put(1L, mockDataForGroup1());
		dataMap.put(2L, mockDataForGroup2());
		dataMap.put(3L, mockDataForGroup3());
	}

	public static GroupSettlementResponse mockDataForGroup1() {
		List<GroupMemberResponse> memberResponseList = getGroupMemberResponsesForGroup1();
		List<GroupSettlementListResponse> settlementList = getGroupSettlementListResponsesForGroup1();
		return new GroupSettlementResponse(memberResponseList, settlementList);
	}

	public static GroupSettlementResponse mockDataForGroup2() {
		List<GroupMemberResponse> memberResponseList = getGroupMemberResponsesForGroup2();
		List<GroupSettlementListResponse> settlementList = getGroupSettlementListResponsesForGroup2();
		return new GroupSettlementResponse(memberResponseList, settlementList);
	}

	public static GroupSettlementResponse mockDataForGroup3() {
		List<GroupMemberResponse> memberResponseList = getGroupMemberResponsesForGroup3();
		List<GroupSettlementListResponse> settlementList = getGroupSettlementListResponsesForGroup3();
		return new GroupSettlementResponse(memberResponseList, settlementList);
	}

	private static List<GroupSettlementListResponse> getGroupSettlementListResponsesForGroup1() {
		GroupSettlementListResponse settlementResponse1 = new GroupSettlementListResponse(
			1L, "1차 회식", 70000L, LocalDate.of(2024, 5, 12)
		);
		GroupSettlementListResponse settlementResponse2 = new GroupSettlementListResponse(
			2L, "2차 회식", 80000L, LocalDate.of(2024, 5, 12)
		);
		GroupSettlementListResponse settlementResponse3 = new GroupSettlementListResponse(
			3L, "3차 회식", 90000L, LocalDate.of(2024, 5, 12)
		);

		return List.of(settlementResponse1, settlementResponse2, settlementResponse3);
	}

	private static List<GroupMemberResponse> getGroupMemberResponsesForGroup1() {
		GroupMemberResponse memberResponse1 = new GroupMemberResponse(
			1L, "민선"
		);
		GroupMemberResponse memberResponse2 = new GroupMemberResponse(
			2L, "나은"
		);
		GroupMemberResponse memberResponse3 = new GroupMemberResponse(
			3L, "해성"
		);
		GroupMemberResponse memberResponse4 = new GroupMemberResponse(
			4L, "한비"
		);
		GroupMemberResponse memberResponse5 = new GroupMemberResponse(
			5L, "나영"
		);
		GroupMemberResponse memberResponse6 = new GroupMemberResponse(
			6L, "건"
		);

		return List.of(memberResponse1, memberResponse2, memberResponse3, memberResponse4,
			memberResponse5, memberResponse6);
	}

	private static List<GroupSettlementListResponse> getGroupSettlementListResponsesForGroup2() {
		GroupSettlementListResponse settlementResponse1 = new GroupSettlementListResponse(
			4L, "첫 모임 1차", 60000L, LocalDate.of(2024, 6, 23)
		);
		GroupSettlementListResponse settlementResponse2 = new GroupSettlementListResponse(
			5L, "첫 모임 2차", 70000L, LocalDate.of(2024, 6, 23)
		);

		return List.of(settlementResponse1, settlementResponse2);
	}

	private static List<GroupMemberResponse> getGroupMemberResponsesForGroup2() {
		GroupMemberResponse memberResponse1 = new GroupMemberResponse(
			1L, "민선"
		);
		GroupMemberResponse memberResponse2 = new GroupMemberResponse(
			8L, "영희"
		);
		GroupMemberResponse memberResponse3 = new GroupMemberResponse(
			9L, "길동"
		);

		return List.of(memberResponse1, memberResponse2, memberResponse3);
	}

	private static List<GroupSettlementListResponse> getGroupSettlementListResponsesForGroup3() {
		GroupSettlementListResponse settlementResponse1 = new GroupSettlementListResponse(
			6L, "팀 회의", 50000L, LocalDate.of(2024, 7, 1)
		);
		GroupSettlementListResponse settlementResponse2 = new GroupSettlementListResponse(
			7L, "팀 디너", 60000L, LocalDate.of(2024, 7, 1)
		);

		return List.of(settlementResponse1, settlementResponse2);
	}

	private static List<GroupMemberResponse> getGroupMemberResponsesForGroup3() {
		GroupMemberResponse memberResponse1 = new GroupMemberResponse(
			1L, "민선"
		);
		GroupMemberResponse memberResponse2 = new GroupMemberResponse(
			11L, "정훈"
		);
		GroupMemberResponse memberResponse3 = new GroupMemberResponse(
			12L, "상현"
		);

		return List.of(memberResponse1, memberResponse2, memberResponse3);
	}
}
