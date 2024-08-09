package com.core.backend.settlement.ui.dto;

import com.core.backend.group.domain.Group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RequestedSettlementGroupInfo {

	private Long id;
	private String groupName;

	public static RequestedSettlementGroupInfo of(Group group) {
		return new RequestedSettlementGroupInfo(group.getId(), group.getGroupName());
	}
}
