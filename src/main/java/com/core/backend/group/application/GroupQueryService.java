package com.core.backend.group.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.usergroup.domain.UserGroup;
import com.core.backend.usergroup.domain.repository.UserGroupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupQueryService {

	private final GroupRepository groupRepository;
	private final UserGroupRepository userGroupRepository;

	public List<GroupInfoResponse> getAllGroup(Long userId) {

		List<Group> groupList = userGroupRepository.findAllByUserId(userId).stream()
			.map(UserGroup::getGroup)
			.toList();

		return groupList.stream()
			.map(GroupInfoResponse::from)
			.collect(Collectors.toList());
	}
}
