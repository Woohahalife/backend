package com.core.backend.group.ui.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.core.backend.settlement.domain.Settlement;
import com.core.backend.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupSettlementResponse {

	private List<GroupMemberResponse> groupMembers;
	private List<GroupSettlementListResponse> settlements;

	public static GroupSettlementResponse of(List<User> users, List<Settlement> settlements) {
		List<GroupMemberResponse> groupMemberResponses = users.stream()
			.map(GroupMemberResponse::from)
			.collect(Collectors.toList());

		List<GroupSettlementListResponse> settlementResponses = settlements.stream()
			.map(GroupSettlementListResponse::from)
			.collect(Collectors.toList());

		return new GroupSettlementResponse(groupMemberResponses, settlementResponses);
	}
}
