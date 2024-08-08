package com.core.backend.group.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupInviteResponse {
	private String invitationCode;

	public static GroupInviteResponse from(String invitationCode) {
		return new GroupInviteResponse(invitationCode);
	}
}
