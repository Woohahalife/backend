package com.core.backend.group.ui.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupInfoResponse {

	private Long id;
	private String groupName;
	private LocalDate createAt;
	private int numOfParticipantCount;
	private boolean bookmark;

}
