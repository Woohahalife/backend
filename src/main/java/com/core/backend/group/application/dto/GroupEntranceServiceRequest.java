package com.core.backend.group.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupEntranceServiceRequest {

	private String invitationCode;

	public static GroupEntranceServiceRequest from(String invitationCode) {
		return new GroupEntranceServiceRequest(invitationCode);
	}
}
