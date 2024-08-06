package com.core.backend.group.ui.dto;

import java.util.List;

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

}
