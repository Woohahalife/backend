package com.core.backend.group.ui.dto;

import com.core.backend.group.domain.Group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupEntranceResponse {

	private Long groupId;

	public static GroupEntranceResponse from(Group group) {
		return new GroupEntranceResponse(group.getId());
	}
}
