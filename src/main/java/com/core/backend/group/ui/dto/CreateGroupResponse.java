package com.core.backend.group.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateGroupResponse {

	private Long groupId;

	public static CreateGroupResponse from(Long groupId) {
		return new CreateGroupResponse(groupId);
	}
}
