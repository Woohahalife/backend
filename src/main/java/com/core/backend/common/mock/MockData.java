package com.core.backend.common.mock;

import com.core.backend.group.ui.dto.GroupInfoResponse;

import java.time.LocalDate;
import java.util.List;

public class MockData {

    public static List<GroupInfoResponse> getAllGroupMockData() {
        GroupInfoResponse response1 = new GroupInfoResponse(
                1L, "HNRC", LocalDate.of(2024, 5, 12), 5, false
        );
        GroupInfoResponse response2 = new GroupInfoResponse(
                2L, "돈가스 모임", LocalDate.of(2024, 6, 23), 6, false
        );
        GroupInfoResponse response3 = new GroupInfoResponse(
                3L, "개발팀 회식", LocalDate.of(2024, 7, 1), 4, true
        );
        GroupInfoResponse response4 = new GroupInfoResponse(
                4L, "대학 동기 회식", LocalDate.of(2024, 7, 10), 7, false
        );
        GroupInfoResponse response5 = new GroupInfoResponse(
                5L, "가족 모임", LocalDate.of(2024, 7, 11), 4, false
        );

        return List.of(response1, response2, response3, response4, response5);
    }
}
