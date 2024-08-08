package com.core.backend.group.ui.dto;

import com.core.backend.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupMemberResponse {

	private Long id;
	private String memberName;

	public static GroupMemberResponse fromUser(User user) {
		return new GroupMemberResponse(user.getId(), user.getName());
	}
}
