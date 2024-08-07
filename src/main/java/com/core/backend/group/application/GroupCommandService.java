package com.core.backend.group.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.group.application.dto.GroupRegisterServiceRequest;
import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.group.ui.dto.GroupInviteResponse;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GroupCommandService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;

	public void createGroup(Long userId, GroupRegisterServiceRequest request) {
		User user = userRepository.findById(userId);
		Group group = Group.of(request.getGroupName());
		group.addUserToGroup(user);

		groupRepository.save(group);
	}

	public GroupInviteResponse generateInviteCode(Long userId, Long groupId) {
		// TODO : 해당 사용자가 참석한 group인지 확인하기 위해 변경 필요
		Group group = groupRepository.findById(groupId);

		// 참여코드 생성

		return GroupInviteResponse.of(group.generateInviteCode());
	}
}
