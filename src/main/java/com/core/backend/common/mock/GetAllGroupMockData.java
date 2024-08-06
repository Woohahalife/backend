package com.core.backend.common.mock;

import java.time.LocalDate;
import java.util.List;

import com.core.backend.group.ui.dto.GroupInfoResponse;

public class GetAllGroupMockData {

	@SuppressWarnings("checkstyle:RegexpMultiline")
	public static List<GroupInfoResponse> mockData() {

		GroupInfoResponse response1 = new GroupInfoResponse(1L, "HNRC", LocalDate.of(2024, 5, 12), 5, false);
		GroupInfoResponse response2 = new GroupInfoResponse(2L, "돈가스 모임", LocalDate.of(2024, 6, 23), 6, false);
		GroupInfoResponse response3 = new GroupInfoResponse(3L, "개발팀 회식", LocalDate.of(2024, 7, 1), 4, true);

		return List.of(response1, response2, response3);
	}
}
